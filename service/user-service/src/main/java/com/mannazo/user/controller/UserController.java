package com.mannazo.user.controller;

import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;
import com.mannazo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        UserResponseDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID userId) {
        UserResponseDTO user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID userId, @RequestBody UserRequestDTO user) {
        UserResponseDTO updateUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        String text = userId+"가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }
}
