package com.mannazo.mannazo.domain.login.controller;

import com.mannazo.mannazo.domain.login.dto.KakaoUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KakaoLoginController {

    private final KakaoService kakaoService;
    @GetMapping("/callback")
    public ResponseEntity<KakaoUserInfoResponseDto> callback(@RequestParam("code") String code) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

    // Provider 별 로그인 URL 전송
    @GetMapping("/login/{provider}")
    public ResponseEntity<String> auth(@PathVariable String provider) {
        // 외부 로그인 인가코드 받는 URL 전송
        return ResponseEntity.status(HttpStatus.OK).body(kakaoService.ResponsePrividerURL("kakao"));
    }
}