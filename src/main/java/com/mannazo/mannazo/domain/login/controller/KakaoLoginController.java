package com.mannazo.mannazo.domain.login.controller;

import com.mannazo.mannazo.api.LoginAPI;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.domain.login.dto.KakaoUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login/kakao")
public class KakaoLoginController implements LoginAPI {

    private final KakaoService kakaoService;
    private final UserService userService;

    @GetMapping("/callback")
    public ResponseEntity<UUID> callback(@RequestParam("code") String authCode) {
        // 엑세스 토큰 발급
        String accessToken = kakaoService.getAccessToken(authCode);
        
        // 액세스 토큰 -> 유저 조회
        KakaoUserInfoResponseDto kakaoUserInfo = kakaoService.getUserInfo(accessToken);

        // 기존 회원 인지 검증하고 데이터 반환
        UserResponseDTO user = kakaoService.findOrRegisterUser(kakaoUserInfo);

        // 사용자 아이디만 반환
        return ResponseEntity.status(HttpStatus.OK).body(user.getUserId());
    }

    // Provider 별 로그인 URL 전송
    @GetMapping("/auth")
    public ResponseEntity<String> auth() {
        // 외부 로그인 인가코드 받는 URL 전송 -> 인가코드 발급
        return ResponseEntity.status(HttpStatus.OK).body(kakaoService.getRedirectUrl());
    }
}