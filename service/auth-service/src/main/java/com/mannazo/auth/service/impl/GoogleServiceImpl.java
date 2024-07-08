package com.mannazo.auth.service.impl;

import com.mannazo.auth.client.user.UserClient;
import com.mannazo.auth.dto.KakaoUserDTO;
import com.mannazo.auth.repository.AuthRepository;
import com.mannazo.auth.service.GoogleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleServiceImpl implements GoogleService {

    private final UserClient userClient;
    private final AuthRepository authRepository;

    @Value("${google.client.id}")
    private String CLIENT_ID;
    @Value("${google.redirect.uris}")
    private String REDIRECT_URI;
    @Value("${google.auth.uri}")
    private String GOOGLE_AUTH_URL;
    @Value("${google.token.uri}")
    private String GOOGLE_TOKEN_URI;
    @Value("${google.auth.provider.cert.url}")
    private String GOOGLE_CERT_URL;


    @Override
    public String getRedirectUrl() {
        log.info("Google 로그인 페이지 요청");
        return GOOGLE_AUTH_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code&scope=email profile";
    }

    @Override
    public String getAccessToken(String AuthCode) {
        return "";
    }

    @Override
    public KakaoUserDTO getUserInfo(String AccessToken) {
        return null;
    }

    @Override
    public Optional<UUID> findBySocialId(String SocialId) {
        return Optional.empty();
    }
}
