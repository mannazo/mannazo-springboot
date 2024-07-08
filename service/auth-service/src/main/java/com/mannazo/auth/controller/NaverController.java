package com.mannazo.auth.controller;

import com.mannazo.auth.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/naver")
@RequiredArgsConstructor
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/getRedirectUrl")
    public ResponseEntity<String> getRedirectUrl(@RequestParam("code") String code, @RequestParam("state") String state) {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
