package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.domain.login.dto.NaverTokenResponseDto;
import com.mannazo.mannazo.domain.login.dto.NaverUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.NaverService;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Slf4j
@AutoConfigureWebTestClient
public class NaverLoginTest {

    @Value("${naver.client.code.test}")
    private String testCode;

    @Value("${naver.redirect.uri}")
    private String redirectUri;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Autowired
    private UserService userService;

    @Autowired
    private NaverService naverService;

    private final String accessToken = "AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc=";
    private final String tokenUri = "https://nid.naver.com/oauth2.0/token";
    private final String UserInfoUri = 	"https://openapi.naver.com/v1/nid/me";

    @Test
    public void testGetNaverUrl() {
        String naverUrl = naverService.getRedirectUrl();
        log.info(naverUrl);
    }
}


