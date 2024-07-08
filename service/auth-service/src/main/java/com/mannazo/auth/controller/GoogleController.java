package com.mannazo.auth.controller;

import com.mannazo.auth.service.GoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google")
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping("/getRedirectUrl")
    public ResponseEntity<String> getRedirectUrl() {
        return ResponseEntity.status(HttpStatus.OK).body(googleService.getRedirectUrl());
    }
}
