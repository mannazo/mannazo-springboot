package com.mannazo.admin.controller;

import com.mannazo.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/")
    public String Admin() {
        return "Hello Admin-Service";
    }

    @GetMapping("/count/posts")
    public ResponseEntity<Integer> getNumberOfPosts() {
        int numberOfPosts = adminService.getNumberOfPosts();
        return ResponseEntity.status(HttpStatus.OK).body(numberOfPosts);
    }

    @GetMapping("/count/{nationality}")
    public ResponseEntity<Integer> getNumberOfUsers(@PathVariable String nationality) {
        int numberOfUsers = adminService.getNumberOfUsers(nationality);
        return ResponseEntity.status(HttpStatus.OK).body(numberOfUsers);
    }

    @GetMapping("/count/allUsers")
    public ResponseEntity<Integer> getNumberOfAllUsers() {
        int numberOfAllUsers = adminService.getNumberOfAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(numberOfAllUsers);
    }
}
