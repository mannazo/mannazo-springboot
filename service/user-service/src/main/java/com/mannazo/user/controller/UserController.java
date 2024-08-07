package com.mannazo.user.controller;

import com.mannazo.user.client.auth.AuthClient;
import com.mannazo.user.client.auth.LoginRequestDTO;
import com.mannazo.user.client.auth.LoginResponseDTO;
import com.mannazo.user.dto.UserCreationRequestDTO;
import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;
import com.mannazo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String User() {
        return "Hello User-Service";
    }

    @PostMapping("/")
    public ResponseEntity<LoginResponseDTO> createUser(@RequestBody UserCreationRequestDTO newUser) {
        log.info("유저 생성 \n {}", newUser);
        LoginResponseDTO loginResponseDTO = userService.createUser(newUser);
        log.info("사용자 로그인 : \n {}",loginResponseDTO.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID userId) {
        UserResponseDTO user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID userId, @RequestBody UserRequestDTO user) {
        UserResponseDTO updateUser = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<UUID> deleteUser(@PathVariable UUID userId) {
        log.info("유저 삭제 요청이 들어왔습니다. \n {}", userId);
        userService.deleteUser(userId);
        log.info("유저 삭제 완료 \n {}", userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userId);
    }

    @GetMapping("/count/{nationality}")
    public int getNumberOfUsers(@PathVariable String nationality) {
        return userService.getNumberOfUsers(nationality);
    }

    @GetMapping("/count")
    public int getNumberOfAllUsers() {
        return userService.getNumberOfAllUsers();
    }

    @PostMapping("/multiple")
    public Map<UUID, UserResponseDTO> getUsers(@RequestBody List<UUID> userIds) {
        Map<UUID, UserResponseDTO> userMap = userService.getUsers(userIds);
        return userMap;
    }

    @GetMapping("/getAllUserIds")
    public List<UUID> getAllUserIds() {
        List<UUID> userIds = userService.getAllUserIds();
        return userIds;
    }
}
