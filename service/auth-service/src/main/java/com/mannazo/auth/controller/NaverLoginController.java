package com.mannazo.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NaverLoginController {
    @GetMapping("/getRedirectUrl")
    public void getRedirectUrl(@RequestParam("code") String code) {

    }

    @GetMapping("/login")
    public void login(){

    }
}
