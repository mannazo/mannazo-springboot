package com.mannazo.mannazo.domain.account.controller;


import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {
    // 유저 조회
    @GetMapping("userid")
    public UserResponseDTO getUserId() {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(UUID.randomUUID());
        return dto;
    }
    // 유저 생성
    // 유저 수정
    // 유저 삭제
}
