package com.mannazo.auth.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginDTO {
    private boolean firstTimeUser;
    private UUID userId;
    private String token;
    private KakaoUserDTO kakaoUser;
}
