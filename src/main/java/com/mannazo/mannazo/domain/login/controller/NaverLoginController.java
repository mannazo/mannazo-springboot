package com.mannazo.mannazo.domain.login.controller;

import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.domain.account.service.UserServiceImpl;
import com.mannazo.mannazo.domain.login.dto.NaverTokenResponseDto;
import com.mannazo.mannazo.domain.login.dto.NaverUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/naver")
public class NaverLoginController{

    private final NaverService naverService;
    private final UserService userService;

    @GetMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
        // 네이버로부터 AccessToken을 받아옴
        NaverTokenResponseDto accessToken = naverService.getAccessTokenFromNaver(code, state);

        // AccessToken을 사용하여 네이버 사용자 정보를 가져옴
        NaverUserInfoResponseDto naverUserInfo = naverService.getUserInfo(accessToken.getAccessToken());

        // 네이버 사용자의 소셜 로그인 ID로 이미 등록된 사용자인지 확인

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/auth")
    public String auth(){
        return naverService.getNaverUrl();
    }



}


