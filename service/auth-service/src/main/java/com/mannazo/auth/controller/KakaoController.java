package com.mannazo.auth.controller;

import com.mannazo.auth.client.UserClient;
import com.mannazo.auth.client.UserResponseDTO;
import com.mannazo.auth.dto.KakaoUserDTO;
import com.mannazo.auth.dto.LoginDTO;
import com.mannazo.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/getRedirectUrl")
    public ResponseEntity<String> getRedirectUrl() {
        return ResponseEntity.status(HttpStatus.OK).body(kakaoService.getRedirectUrl());
    }

    @GetMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestParam("code") String authCode) {
        // 엑세스 토큰 발급
        String accessToken = kakaoService.getAccessToken(authCode);
        log.info("액세스 토큰 : {}",accessToken);

        // 액세스 토큰 -> 유저 조회
        KakaoUserDTO kakaoUserInfo = kakaoService.getUserInfo(accessToken);
        log.info("카카오 사용자 불러옴 : {}",kakaoUserInfo);

        // 기존 회원 인지 검증하고 데이터 반환
        UserResponseDTO user = kakaoService.findOrRegisterUser(kakaoUserInfo);

        LoginDTO loginDTO = new LoginDTO();
//        loginDTO.setUserId();
//        loginDTO.setToken();

        // 사용자 아이디만 반환
        return ResponseEntity.status(HttpStatus.OK).body(loginDTO);
    }
}