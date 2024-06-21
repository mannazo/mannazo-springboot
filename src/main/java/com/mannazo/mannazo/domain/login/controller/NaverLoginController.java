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
    public NaverUserInfoResponseDto getUserInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        NaverTokenResponseDto accessToken = naverService.getAccessTokenFromNaver(code, state);
        return naverService.getUserInfo(accessToken.getAccessToken());
    }

//    @GetMapping("/getUserInfo")
//    public NaverUserInfoResponseDto getUserInfo(@RequestParam("accessToken") String accessToken) {
//        return naverService.getUserInfo(accessToken);
//    }


    @GetMapping("/auth")
    public String auth(){
        return naverService.getNaverUrl();
    }
}

