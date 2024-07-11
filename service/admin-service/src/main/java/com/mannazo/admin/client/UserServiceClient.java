package com.mannazo.admin.client;

import com.mannazo.admin.client.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") String userId);

    @GetMapping("/count/{nationality}")
    int getNumberOfUsers(@PathVariable String nationality);
    @GetMapping("/count")
    int getNumberOfAllUsers();
}
