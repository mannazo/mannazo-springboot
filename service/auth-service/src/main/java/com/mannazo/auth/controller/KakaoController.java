package com.mannazo.auth.controller;

import com.mannazo.auth.dto.KakaoUserDTO;
import com.mannazo.auth.dto.LoginDTO;
import com.mannazo.auth.service.KakaoService;
import com.mannazo.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;
    private final JwtUtil jwtUtil;

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
        log.info("카카오 사용자 불러옴 : {}",kakaoUserInfo.toString());

        // 이미 가입된 회원인지 검증
        Optional<UUID> userId = kakaoService.findBySocialId(String.valueOf(kakaoUserInfo.getId()));
        LoginDTO loginDTO = new LoginDTO();
        if (userId.isPresent()) {
            log.info("이미 가입된 회원입니다.");
            loginDTO.setFirstTimeUser(false);
            loginDTO.setUserId(userId.get());
            loginDTO.setToken(jwtUtil.createAccessToken(userId.get()));

        } else {
            log.info("가입되지 않은 회원입니다.");
            loginDTO.setFirstTimeUser(true);
            loginDTO.setKakaoUser(kakaoUserInfo);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(loginDTO);
    }
}