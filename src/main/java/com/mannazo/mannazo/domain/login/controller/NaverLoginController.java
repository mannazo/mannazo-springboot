package com.mannazo.mannazo.domain.login.controller;

import com.mannazo.mannazo.domain.login.dto.NaverTokenResponseDto;
import com.mannazo.mannazo.domain.login.dto.NaverUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/naver")
public class NaverLoginController{

    private final NaverService naverService;

    @GetMapping("/callback")
    public NaverTokenResponseDto callback(@RequestParam("code") String code, @RequestParam("state") String state) {
        log.info("Code: {}", code);
        log.info("State: {}", state);
        return naverService.getAccessTokenFromNaver(code, state);
    }

    @GetMapping("/getUserInfo")
    public NaverUserInfoResponseDto getUserInfo(@RequestParam("accessToken") String accessToken) {
        return naverService.getUserInfo(accessToken);
    }


    @GetMapping("/auth")
    public String auth(){
        return naverService.getNaverUrl();
    }

}

