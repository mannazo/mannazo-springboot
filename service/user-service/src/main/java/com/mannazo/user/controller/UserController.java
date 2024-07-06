package com.mannazo.user.controller;

import com.mannazo.user.domain.UserRequestDTO;
import com.mannazo.user.domain.UserResponseDTO;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable UUID userId) {
        UserResponseDTO user = userService.getUser(userId);
        return user;
    }

    @GetMapping("")
    public List<UserResponseDTO> findAll() {
        List<UserResponseDTO> users = userService.findAll();

        return users;
    }

    @PostMapping("")
    public UUID addUser(@RequestBody UserRequestDTO user) {
        userService.addUser(user);
        return user.getUserId();
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return userId+"가 삭제됨";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable UUID userId, @RequestBody UserRequestDTO user) {
        userService.updateUser(userId, user);
        return userId+"가 업데이트 되었습니다.";
    }
}
