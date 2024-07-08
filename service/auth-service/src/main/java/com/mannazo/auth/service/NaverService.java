package com.mannazo.auth.service;

import com.mannazo.auth.dto.KakaoUserDTO;

import java.util.Optional;
import java.util.UUID;

public interface NaverService {
    String getRedirectUrl();

    String getAccessToken(String AuthCode);

    KakaoUserDTO getUserInfo(String AccessToken);

    Optional<UUID> findBySocialId(String SocialId);
}
