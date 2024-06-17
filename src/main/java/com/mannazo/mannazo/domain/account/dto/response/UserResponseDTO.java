package com.mannazo.mannazo.domain.account.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * password 제외
 * authority 제외
 */
@Getter
@Setter
public class UserResponseDTO {
    private UUID userId;
    private String email;
    private String name;
    private String nickname;
    private Integer Nationality;
    private String language;
    private String profile_image;
    private String introduction;
    private String city;
    private Enum gender;
    private Enum mbti;
    private String interests;
    private Timestamp last_login_at;
}
