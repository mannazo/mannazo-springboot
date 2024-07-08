package com.mannazo.auth.service.impl;

import com.mannazo.auth.client.UserClient;
import com.mannazo.auth.client.UserResponseDTO;
import com.mannazo.auth.dto.KakaoToken;
import com.mannazo.auth.dto.KakaoUserDTO;
import com.mannazo.auth.entity.SocialEntity;
import com.mannazo.auth.repository.AuthRepository;
import com.mannazo.auth.service.KakaoService;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoServiceImpl implements KakaoService {

    private final UserClient userClient;
    private final AuthRepository authRepository;

    @Value("${kakao.client_id}")
    private String CLIENT_ID;
    @Value("${kakao.redirect_uri}")
    private String REDIRECT_URI;
    protected String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    protected String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    @Override
    public String getRedirectUrl() {
        log.info("kakao 로그인 페이지 요청");
        return "https://kauth.kakao.com/oauth/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    @Override
    public String getAccessToken(String AuthCode) {
        KakaoToken kakaoToken = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", CLIENT_ID)
                        .queryParam("code", AuthCode)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoToken.class)
                .block();

        // 로그
        log.info("[Kakao Service] Access Token ------> {}", kakaoToken.getAccessToken());
        log.info("[Kakao Service] Refresh Token ------> {}", kakaoToken.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info("[Kakao Service] Id Token ------> {}", kakaoToken.getIdToken());
        log.info("[Kakao Service] Scope ------> {}", kakaoToken.getScope());

        return kakaoToken.getAccessToken();
    }

    @Override
    public KakaoUserDTO getUserInfo(String accessToken) {

        KakaoUserDTO kakaoUserDTO = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoUserDTO.class)
                .block();

        // 로그
        log.info("[ Kakao Service ] Auth ID ---> {} ", kakaoUserDTO.getId());
        log.info("[ Kakao Service ] NickName ---> {} ", kakaoUserDTO.getKakaoAccount().getProfile().getNickName());
        log.info("[ Kakao Service ] Email ---> {} ", kakaoUserDTO.getKakaoAccount().getEmail());

        return kakaoUserDTO;
    }

    @Override
    public UserResponseDTO findOrRegisterUser(KakaoUserDTO kakaoUserInfo) {
        return null;
    }

//    @Override
//    public UserResponseDTO findOrRegisterUser(KakaoUserDTO kakaoUserInfo) {
//        // 소셜아이디로 회원가입 여부 확인
//        Optional<SocialEntity> socialEntityOpt = authRepository.findBySosialId(String.valueOf(kakaoUserInfo.getId()));
//
//        UUID userId;
//
//        // 회원이 있을 경우 반환, 또는 회원 가입 후 반환
//        if(socialEntityOpt.isPresent()) {
//            SocialEntity existingUserEntity = socialEntityOpt.get();
//            // UserEntity -> DTO 변환 후 반환
//            userId = existingUserEntity.getUserid();
//        } else {
//            // 여기에서 회원 가입 로직을 실행 후, 생성된 UserEntity -> DTO 변환 후 반환
//            // ...
//        }
//
//        // 기록이 없으면, 회원가입하고 UserEntity를 DTO로 바꿔서 리턴
//        KakaoUserInfoResponseDto.KakaoAccount user = socialUserInfo.getKakaoAccount();
//        UserEntity newUser = UserEntity.builder()
//                .userId(UUID.randomUUID())
//                .name(user.getName())
//                .nickname(user.getProfile().getNickName())
//                .gender(user.getGender())
//                .profilePhoto(user.getProfile().getProfileImageUrl())
//                .socialLoginId(String.valueOf(socialUserInfo.getId()))
//                .lastLoginTime(new Timestamp(System.currentTimeMillis()))
//                .build();
//
//        return UserResponseDTO.fromEntity(userRepository.save(newUser));
//    }
}
