package com.mannazo.mannazo.domain.login.service;

import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import com.mannazo.mannazo.domain.login.dto.KakaoTokenResponseDto;
import com.mannazo.mannazo.domain.login.dto.KakaoUserInfoResponseDto;
import com.mannazo.mannazo.global.util.JwtUtil;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoService implements SocialLoginService{

    private final UserRepository userRepository;

    @Value("${kakao.client_id}")
    private String CLIENT_ID;
    @Value("${kakao.redirect_uri}")
    private String REDIRECT_URI;
    protected String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    protected String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    @Override
    public String getRedirectUrl() {
        return "https://kauth.kakao.com/oauth/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    @Override
    public String getAccessToken(String AuthCode) {
        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", CLIENT_ID)
                        .queryParam("code", AuthCode)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        // 로그
        log.info("[Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info("[Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info("[Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());
        log.info("[Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());

        return kakaoTokenResponseDto.getAccessToken();
    }

    @Override
    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {

        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        // 로그
        log.info("[ Kakao Service ] Auth ID ---> {} ", userInfo.getId());
        log.info("[ Kakao Service ] NickName ---> {} ", userInfo.getKakaoAccount().getProfile().getNickName());
        log.info("[ Kakao Service ] ProfileImageUrl ---> {} ", userInfo.getKakaoAccount().getProfile().getProfileImageUrl());
        log.info("[ Kakao Service ] Email ---> {} ", userInfo.getKakaoAccount().getEmail());

        return userInfo;
    }

    public UserResponseDTO findOrRegisterUser(KakaoUserInfoResponseDto socialUserInfo) {

        // 소셜아이디로 회원가입 여부 확인
        Optional<UserEntity> userEntity = userRepository.findBySocialLoginId(String.valueOf(socialUserInfo.getId()));

        // 기록이 있으면, UserEntity -> DTO 변환 후 반환
        if (userEntity.isPresent()) {
            return UserResponseDTO.fromEntity(userEntity.get());
        }

        // 기록이 없으면, 회원가입하고 UserEntity를 DTO로 바꿔서 리턴
        KakaoUserInfoResponseDto.KakaoAccount user = socialUserInfo.getKakaoAccount();
        UserEntity newUser = UserEntity.builder()
                .userId(UUID.randomUUID())
                .name(user.getName())
                .nickname(user.getProfile().getNickName())
                .gender(user.getGender())
                .profilePhoto(user.getProfile().getProfileImageUrl())
                .socialLoginId(String.valueOf(socialUserInfo.getId()))
                .lastLoginTime(new Timestamp(System.currentTimeMillis()))
                .build();

        return UserResponseDTO.fromEntity(userRepository.save(newUser));
    }
}
