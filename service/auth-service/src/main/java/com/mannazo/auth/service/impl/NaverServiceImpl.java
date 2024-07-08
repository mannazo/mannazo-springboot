package com.mannazo.auth.service.impl;

import com.mannazo.auth.dto.KakaoUserDTO;
import com.mannazo.auth.service.NaverService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NaverServiceImpl implements NaverService {
    @Override
    public String getRedirectUrl() {
        return "";
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
