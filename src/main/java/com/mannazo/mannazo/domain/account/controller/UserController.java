package com.mannazo.mannazo.domain.account.controller;

import com.mannazo.mannazo.api.UserAPI;
import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class UserController implements UserAPI {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        UserResponseDTO response = userService.retrieveUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @PutMapping("/user")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user) {
        log.info("[Log] 사용자 정보 수정 요청 {} \n", user.toString());
        UserResponseDTO updateUser = userService.modifyUserDetails(user);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @Override
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO.Delete> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(id));
    }
}