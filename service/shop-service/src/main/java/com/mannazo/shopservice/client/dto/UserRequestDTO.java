package com.mannazo.shopservice.client.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
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
    private LocalDate birthday;
    private Timestamp lastLoginAt;
}
