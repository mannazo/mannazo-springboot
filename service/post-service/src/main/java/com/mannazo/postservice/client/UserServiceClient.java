package com.mannazo.postservice.client;

import com.mannazo.postservice.client.dto.UserResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") String userId);

    @PostMapping("/multiple")
    Map<UUID, UserResponseDTO> getUsers(@RequestBody List<UUID> userIds);

    @GetMapping("/getAllUserIds")
    List<UUID> getAllUserIds();
}
