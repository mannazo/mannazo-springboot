package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void 유저_서비스_테스트(){
        UserRequestDTO user = UserRequestDTO.builder()
                .email("test@test.com")
                .password("qkrwjdgus")
                .name("박정현")
                .nickname("nick")
                .nationality("Republic of Korea")
                .language("Kor")
                .profilePhoto("photoLink")
                .introduction("introduction")
                .city("Seoul")
                .gender("남자")
                .mbti("ISTJ")
                .interests("Reading, Coding")
                .build();

        userService.registerUser(user);
    }

    @Test
    public void 유저_로그인_테스트() {
        UserRequestDTO.Login user = new UserRequestDTO.Login();
        user.setEmail("test@test.com");
        user.setPasssword("qkrwjdgus");
        log.info(userService.loginUser(user).toString());
    }
}