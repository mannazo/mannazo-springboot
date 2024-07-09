package com.mannazo.auth.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name="user-service", url = "http://localhost:8080/user")
public interface UserClient {

    @GetMapping("/{userId}")
    Optional<UserResponseDTO> getUser(@PathVariable UUID userId);
}
