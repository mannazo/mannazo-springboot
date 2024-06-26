package com.mannazo.mannazo.domain.account.controller;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    private final UserService userService;

    // 사용자 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        log.info("[LOG] 사용자 정보 조회 요청 : 유저 아이디 " + id + "\n");
        UserResponseDTO response = userService.retrieveUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 사용자 수정
    @PutMapping("/user")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user) {
        log.info("[Log] 사용자 정보 수정 요청" + user.toString());
        UserResponseDTO updateUser = userService.modifyUserDetails(user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    // 사용자 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO.Delete> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(id));
    }
}