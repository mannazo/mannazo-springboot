package com.mannazo.mannazo.domain.login.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class NaverUserInfoResponseDto {
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String gender;
    private String age;
    private String birthday;
    private String profileImage;
}
