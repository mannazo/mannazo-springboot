package com.mannazo.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class UserResponseDTO {
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
    private LocalDate birthday;
    private Timestamp lastLoginAt;
}