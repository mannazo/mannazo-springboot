package com.mannazo.user.domain;

import lombok.Getter;

import java.util.UUID;
@Getter
public class UserRequestDTO {
    private UUID userId;
    private String email;
    private String name;
    private String nickname;
    private String nationality;
    private String language;
    private String profileImage;
    private String introduction;
    private String city;
    private String authority;
    private String gender;
    private String mbti;
    private String interests;
    private String birthday;
    private String lastLoginAt;
}
