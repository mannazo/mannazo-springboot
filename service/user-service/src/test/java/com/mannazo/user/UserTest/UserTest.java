package com.mannazo.user.UserTest;

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

    @Test
    public void 유저_생성() {
        UserRequestDTO userRequestDTO = UserRequestDTO.builder()
                .userId(UUID.randomUUID())
                .email("test@test.com")
                .name("Test Name")
                .nickname("testnickname")
                .nationality("Korean")
                .language("Korean")
                .profileImage("profile_image_url")
                .introduction("Hello, I am a test user.")
                .city("Seoul")
                .authority("User")
                .gender("남자")
                .mbti("ENTJ")
                .interests("Reading, Coding")
                .birthday(LocalDate.of(1990, 1, 1))
                .lastLoginAt(new Timestamp(System.currentTimeMillis()))
                .build();

        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        log.info("Created User: " + createdUser);
    }
}
