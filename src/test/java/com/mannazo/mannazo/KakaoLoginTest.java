package com.mannazo.mannazo;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class KakaoLoginTest {

    @Value("${kakao.client_id}")
    private String client_id;
    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    @Test
    public void KakaoLoginTest() {
        // 카카오 인가 코드 받기
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;


        WebClient webClient = WebClient.create("");

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("https://kauth.kakao.com/oauth/authorize")
                        .queryParam("client_id", client_id)
                        .queryParam("redirect_uri", redirect_uri)
                        .queryParam("code", "code")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(result);
    }

}
