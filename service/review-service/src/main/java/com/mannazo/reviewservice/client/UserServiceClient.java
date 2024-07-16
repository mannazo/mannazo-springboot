package com.mannazo.reviewservice.client;

import com.mannazo.reviewservice.client.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    UserResponseDTO getUserById(@PathVariable("userId") String userId);
}
