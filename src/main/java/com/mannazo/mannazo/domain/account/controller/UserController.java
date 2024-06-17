package com.mannazo.mannazo.domain.account.controller;

import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    // 사용자 조회
    @GetMapping("/user/{id}")
    public UserResponseDTO getUser(@PathVariable UUID id) {

        return null;
    }

    // 사용자 생성
    @PostMapping("/user")
    public UserResponseDTO createUser(@RequestBody UserResponseDTO user) {

        return null;
    }

    // 사용자 수정
    @PutMapping("/user/{id}")
    public UserResponseDTO updateUser(@PathVariable UUID id, @RequestBody UserResponseDTO user) {

        return null;
    }

    // 사용자 삭제
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable UUID id) {

    }
}