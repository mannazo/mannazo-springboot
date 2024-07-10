package com.mannazo.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Builder
public class UserRequestDTO { // 회원 가입 회원 수정
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
    private LocalDate birthday;
}
