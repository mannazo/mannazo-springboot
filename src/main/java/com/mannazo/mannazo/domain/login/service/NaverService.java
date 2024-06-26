package com.mannazo.mannazo.domain.login.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import com.mannazo.mannazo.domain.login.dto.NaverTokenResponseDto;
import com.mannazo.mannazo.domain.login.dto.NaverUserInfoResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverService implements SocialLoginService{

    private final UserRepository userRepository;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Value("${naver.redirect.uri}")
    private String redirectUri;

    private final String UserInfoUri =    "https://openapi.naver.com/v1/nid/me";

    private String state = new BigInteger(130, new SecureRandom()).toString(32);

    public String getRedirectUrl(){
        // 상태 토큰으로 사용할 랜덤 문자열 생성
        String URL = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientId+"&state="+state+"&redirect_uri="+redirectUri;
        return URL;
    }

    @Override
    public String getAccessToken(String AuthCode) {
        NaverTokenResponseDto response = WebClient.create("https://nid.naver.com").post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2.0/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("code", AuthCode)
                        .queryParam("state", state)
                        .queryParam("redirect_uri", redirectUri)
                        .build())
                .retrieve()
                .bodyToMono(NaverTokenResponseDto.class)
                .block();  // 블록킹 호출

        if (response != null) {
            return response.getAccessToken();
        } else {
            throw new RuntimeException("Failed to retrieve access token from Naver");
        }
    }

    public NaverUserInfoResponseDto getUserInfo(String accessToken) {
        NaverUserInfoResponseDto userInfo = WebClient.create(UserInfoUri)
                .get()
                .header(HttpHeaders.AUTHORIZATION,"Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(NaverUserInfoResponseDto.class)
                .block();

        log.info("[ Naver Service ] response ---> {} ", userInfo.getResponse().toString());


        return userInfo;
    }

    public UserResponseDTO saveUserIfNotExists(NaverUserInfoResponseDto naverUserInfo) {
        String socialLoginId = naverUserInfo.getResponse().getId();
        Optional<UserEntity> existingUser = userRepository.findBySocialLoginId(socialLoginId);

        UserEntity userEntity;

        if (existingUser.isPresent()) {
            // 이미 존재하는 사용자가 있으면 그대로 반환
            return UserResponseDTO.fromEntity(existingUser.get());
        } else {
            // 새로운 사용자 정보로 저장
            NaverUserInfoResponseDto.getResponse naverResponse = naverUserInfo.getResponse();

            // Naver에서 제공하는 birthyear와 birthday 정보를 가져옵니다.
            String birthyear = naverResponse.getBirthyear(); // "yyyy" 형식의 연도 문자열
            String birthday = naverResponse.getBirthday(); // "MM-dd" 형식의 월과 일 문자열

            // "yyyy-MM-dd" 형식의 문자열을 LocalDate로 파싱합니다.
            LocalDate birthdate = LocalDate.parse(birthyear + "-" + birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // 나이 계산 (현재 날짜 기준)
            int age = calculateAge(birthdate);

            userEntity = UserEntity.builder()
                    .userId(UUID.randomUUID())
                    .email(naverResponse.getEmail())
                    .name(naverResponse.getName())
                    .nickname(naverResponse.getNickname())
                    .gender(naverResponse.getGender())
                    .birthday(birthdate)
                    .profilePhoto(naverResponse.getProfileImage())
                    .socialLoginId(naverResponse.getId())
                    .lastLoginTime(new Timestamp(System.currentTimeMillis()))
                    .build();

            userRepository.save(userEntity);
        }

        return UserResponseDTO.fromEntity(userEntity);
    }

    //현재 기준 나이계산하는 메서드
    private int calculateAge(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }

}
