package com.mannazo.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name="user-service", url = "http://localhost:8080/user")
public interface UserClient {

    @GetMapping("/{userId}")
    UserResponseDTO getUser(@PathVariable UUID userId);

    @PostMapping("")
    UserResponseDTO createUser(@RequestBody UserRequestDTO user);

}
