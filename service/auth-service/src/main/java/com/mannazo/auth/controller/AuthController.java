package com.mannazo.auth.controller;

import com.mannazo.auth.dto.LoginRequestDTO;
import com.mannazo.auth.dto.LoginResponseDTO;
import com.mannazo.auth.dto.SocialDTO;
import com.mannazo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/")
    public String Auth() {
        return "Hello Auth-Service";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> Login(@RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("소셜 로그인 인증 요청 \n {}",loginRequestDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(authService.getSocialLogin(loginRequestDTO));
    }

    @PostMapping("/save")
    public ResponseEntity<SocialDTO> Save(@RequestBody SocialDTO socialDTO) {
        log.info("소셜 테이블 등록 요청 \n {}",socialDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(authService.saveSocialUser(socialDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> Delete(UUID userId) {
        authService.delSocialLogin(userId);
        return ResponseEntity.status(HttpStatus.OK).body(authService.delSocialLogin(userId));
    }
}
