package com.mannazo.mannazo.domain.login.service;

import com.mannazo.mannazo.domain.login.dto.NaverTokenResponseDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
@Slf4j
public class NaverService {
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
}
