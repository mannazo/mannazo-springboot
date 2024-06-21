package com.mannazo.mannazo.domain.login.controller;

import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.domain.account.service.UserServiceImpl;
import com.mannazo.mannazo.domain.login.dto.NaverTokenResponseDto;
import com.mannazo.mannazo.domain.login.dto.NaverUserInfoResponseDto;
import com.mannazo.mannazo.domain.login.service.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login/naver")
public class NaverLoginController{

    private final NaverService naverService;
    private final UserService userService;

    @GetMapping("/callback")
    public ResponseEntity<UserResponseDTO> getUserInfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        NaverTokenResponseDto accessToken = naverService.getAccessTokenFromNaver(code, state);
        NaverUserInfoResponseDto naverUserInfoResponseDto = naverService.getUserInfo(accessToken.getAccessToken());

        // 소셜에서 가져온 ID가 DB에 있는지 확인
        UUID user = naverService.isSocialLoginId(naverUserInfoResponseDto.getResponse().getId());
        if(user == null) {
            // 회원가입
            naverService.saveUserFromNaverInfo(naverUserInfoResponseDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.retrieveUser(user));
    }


    @GetMapping("/auth")
    public String auth(){
        return naverService.getNaverUrl();
    }



}


