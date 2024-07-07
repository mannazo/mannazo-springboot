package com.mannazo.auth.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
public class KakaoLoginController {

    @GetMapping("/getRedirectUrl")
    public void getRedirectUrl(@RequestParam("code") String code) {

    }

    @GetMapping("/login")
    public void login(){

    }
}
