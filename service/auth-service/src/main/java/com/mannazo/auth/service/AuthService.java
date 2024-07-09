package com.mannazo.auth.service;

import com.mannazo.auth.dto.LoginRequestDTO;
import com.mannazo.auth.dto.LoginResponseDTO;

import java.util.Optional;
import java.util.UUID;

public interface AuthService {
    LoginResponseDTO getSocialLogin(LoginRequestDTO loginRequestDTO);
}
