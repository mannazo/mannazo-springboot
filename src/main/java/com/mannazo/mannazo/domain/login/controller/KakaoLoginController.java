package com.mannazo.mannazo.domain.login.controller;

import com.mannazo.mannazo.domain.login.dto.KakaoUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/login/kakao")
public class KakaoLoginController {

    private final KakaoService kakaoService;
    @GetMapping("/callback")
    public ResponseEntity<KakaoUserInfoResponseDto> callback(@RequestParam("code") String code) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

    // Provider 별 로그인 URL 전송
    @GetMapping("/auth")
    public ResponseEntity<String> auth() {
        // 외부 로그인 인가코드 받는 URL 전송
        return ResponseEntity.status(HttpStatus.OK).body(kakaoService.ResponsePrividerURL());
    }
}