package com.mannazo.postservice.client;

import com.mannazo.postservice.client.dto.UserResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080/user")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") String userId);
}
