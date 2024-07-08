package com.mannazo.auth.service;

import com.mannazo.auth.client.UserResponseDTO;
import com.mannazo.auth.dto.KakaoUserDTO;

public interface KakaoService {
    String getRedirectUrl();

    String getAccessToken(String AuthCode);

    KakaoUserDTO getUserInfo(String AccessToken);

    UserResponseDTO findOrRegisterUser(KakaoUserDTO kakaoUserInfo);
}
