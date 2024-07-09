package com.mannazo.auth.service;

import com.mannazo.auth.dto.LoginRequestDTO;
import com.mannazo.auth.dto.LoginResponseDTO;
import com.mannazo.auth.dto.SocialDTO;

import java.util.Optional;
import java.util.UUID;

public interface AuthService {

    SocialDTO saveSocialUser(SocialDTO socialDTO);

    LoginResponseDTO getSocialLogin(LoginRequestDTO loginRequestDTO);
}
