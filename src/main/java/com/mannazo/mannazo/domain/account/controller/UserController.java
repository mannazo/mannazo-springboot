package com.mannazo.mannazo.domain.account.controller;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    // 사용자 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        UserResponseDTO response = userService.retrieveUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 사용자 생성
    @PostMapping("/user")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO user) {

        return null;
    }

    // 사용자 수정
    @PutMapping("/user")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user) {
        UserResponseDTO updateUser = userService.modifyUserDetails(user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    // 사용자 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO.Delete> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(id));
    }

}