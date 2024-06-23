package com.mannazo.mannazo.domain.login.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
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
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverService {

    private final UserRepository userRepository;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Value("${naver.redirect.uri}")
    private String redirectUri;

    private final String tokenUri = "https://nid.naver.com/oauth2.0/token";
    private final String UserInfoUri = 	"https://openapi.naver.com/v1/nid/me";


    public NaverTokenResponseDto getAccessTokenFromNaver(String code, String state) {
        return WebClient.create("https://nid.naver.com").post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2.0/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("code", code)
                        .queryParam("state", state)
                        .queryParam("redirect_uri", redirectUri)
                        .build())
                .retrieve()
                .bodyToMono(NaverTokenResponseDto.class)
                .block();  // 블록킹 호출
    }


    public String getNaverUrl(){
        // 상태 토큰으로 사용할 랜덤 문자열 생성
        String state = new BigInteger(130, new SecureRandom()).toString(32);
        String URL = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientId+"&state="+state+"&redirect_uri="+redirectUri;
        return URL;
    }

    public UserEntity saveUserFromNaverInfo(NaverUserInfoResponseDto naverUserInfo) {
        UserRequestDTO userRequestDto = convertToUserRequestDto(naverUserInfo);
        UserEntity userEntity = userRequestDto.toEntity();

        return userRepository.save(userEntity);
    }

    public NaverUserInfoResponseDto getUserInfo(String accessToken) {
        NaverUserInfoResponseDto userInfo = WebClient.create(UserInfoUri)
                .get()
                .header(HttpHeaders.AUTHORIZATION,"Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(NaverUserInfoResponseDto.class)
                .block();

        log.info("[ Naver Service ] Auth ID ---> {} ", userInfo.toString());


        return userInfo;
    }

    public UUID isSocialLoginId(String socialLoginId) {
        Optional<UserEntity> user = userRepository.findBySocialLoginId(socialLoginId);
        return user.map(UserEntity::getUserId).orElse(null);
    }

    private UserEntity createUserFromNaverInfo(NaverUserInfoResponseDto naverUserInfo) {
        NaverUserInfoResponseDto.getResponse naverResponse = naverUserInfo.getResponse();

        // Naver에서 제공하는 birthyear와 birthday 정보를 가져옵니다.
        String birthyear = naverResponse.getBirthyear(); // "yyyy" 형식의 연도 문자열
        String birthday = naverResponse.getBirthday(); // "MM-dd" 형식의 월과 일 문자열

        // "yyyy-MM-dd" 형식의 문자열을 LocalDate로 파싱합니다.
        LocalDate birthdate = LocalDate.parse(birthyear + "-" + birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // LocalDate를 java.sql.Date로 변환합니다.
        Date sqlBirthdate = Date.valueOf(birthdate);

        log.info("정보=>>>>>>>>"+naverResponse.getBirthday());
        log.info("정보=>>>>>>>>"+naverResponse.getBirthyear());
        return UserEntity.builder()
                .userId(UUID.randomUUID())
                .email(naverResponse.getEmail())
                .name(naverResponse.getName())
                .nickname(naverResponse.getNickname())
                .gender(naverResponse.getGender())
                .birthday(sqlBirthdate)
                .profilePhoto(naverResponse.getProfileImage())
                .socialLoginId(naverResponse.getId())
                .lastLoginTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    private UserRequestDTO convertToUserRequestDto(NaverUserInfoResponseDto naverUserInfoResponseDto) {
        NaverUserInfoResponseDto.getResponse naverUserInfo = naverUserInfoResponseDto.getResponse();
        return UserRequestDTO.builder()
                .userId(UUID.randomUUID())  // 새로운 UUID 생성
                .email(naverUserInfo.getEmail())
                .gender(naverUserInfo.getGender())
                .name(naverUserInfo.getName())
                .nickname(naverUserInfo.getNickname())  // 만약 닉네임을 제공한다면
                .profilePhoto(naverUserInfo.getProfileImage())  // 만약 프로필 이미지를 제공한다면
                .lastLoginTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public UserEntity saveUserIfNotExists(NaverUserInfoResponseDto naverUserInfo) {
        String socialLoginId = naverUserInfo.getResponse().getId();
        Optional<UserEntity> existingUser = userRepository.findBySocialLoginId(socialLoginId);

        if (existingUser.isPresent()) {
            // 이미 존재하는 사용자가 있으면 그대로 반환
            return existingUser.get();
        } else {
            // 새로운 사용자 정보로 저장
            UserEntity newUser = createUserFromNaverInfo(naverUserInfo);
            return userRepository.save(newUser);
        }
    }
}
