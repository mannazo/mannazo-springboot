package com.mannazo.mannazo.domain.account.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class UserRequestDTO {
    private UUID userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private Integer Nationality;
    private String language;
    private String profile_image;
    private String introduction;
    private String city;
    private Enum authority;
    private Enum gender;
    private Enum mbti;
    private String interests;
    private Timestamp last_login_at;

    // 내부 클래스 정의
    @Getter
    @Setter
    public static class UserIdOnly {
        // 외부 클래스의 필드를 그대로 사용 가능
        private UUID userId;
    }
}
