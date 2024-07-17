package com.mannazo.shopservice.client;

import com.mannazo.shopservice.client.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    UserResponseDTO getUserById(@PathVariable("userId") String userId);

    @GetMapping("/getAllUserIds")
    List<UUID> getAllUserIds();
}
