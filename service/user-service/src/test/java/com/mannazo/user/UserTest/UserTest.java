package com.mannazo.user.UserTest;

import com.mannazo.user.client.auth.LoginRequestDTO;
import com.mannazo.user.dto.UserCreationRequestDTO;
import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;
import com.mannazo.user.repository.UserRepository;
import com.mannazo.user.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
}
