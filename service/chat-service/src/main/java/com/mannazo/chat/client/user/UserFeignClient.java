package com.mannazo.chat.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name="user-service")
public interface UserFeignClient {

    @GetMapping("/{userId}")
    Optional<UserResponseDTO> getUser(@PathVariable UUID userId);
}
