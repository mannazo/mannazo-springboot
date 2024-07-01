package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import com.mannazo.mannazo.domain.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void 유저_서비스_테스트(){
        UserRequestDTO user = UserRequestDTO.builder()
                .email("test@test.com")
                .name("박정현")
                .nickname("nick")
                .nationality("Republic of Korea")
                .language("Kor")
                .profilePhoto("photoLink")
                .introduction("introduction")
                .city("Seoul")
                .gender("남자")
                .mbti("ISTJ")
                .interests(Arrays.asList("Reading, Coding"))
                .build();

        userService.modifyUserDetails(user);
    }

    @Test
    public void 유저_소셜() {
        Optional<UserEntity> user = userRepository.findBySocialLoginId("12321");
        if (user.isEmpty()) {
            log.info("User not found with Naver ID: ");
        } else {
           log.info("있음");
        }
    }


}