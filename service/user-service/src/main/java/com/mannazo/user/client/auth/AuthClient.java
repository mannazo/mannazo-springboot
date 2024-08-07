package com.mannazo.user.client.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name="auth-service")
public interface AuthClient {

    @PostMapping("/save")
    SocialDTO save(@RequestBody SocialDTO socialDTO);

    @PostMapping("/login")
    LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO);

    @DeleteMapping("/delete")
    ResponseEntity<String> delete(@RequestBody UUID id);
}