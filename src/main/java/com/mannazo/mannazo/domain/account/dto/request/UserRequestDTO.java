package com.mannazo.mannazo.domain.account.dto.request;

import lombok.Getter;
import lombok.Setter;
import com.mannazo.mannazo.domain.account.entity.User;
import java.sql.Date;
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
    private Integer nationality;
    private String language;
    private String profilePhoto;
    private String introduction;
    private String city;
    private String authority;
    private String gender;
    private String mbti;
    private String interests;
    private Date birthday;
    private Timestamp lastLoginTime;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .nationality(nationality)
                .language(language)
                .profilePhoto(profilePhoto)
                .introduction(introduction)
                .city(city)
                .authority(User.Authority.valueOf(authority))
                .gender(User.Gender.valueOf(gender))
                .mbti(User.Mbti.valueOf(mbti))
                .interests(interests)
                .birthday(birthday)
                .lastLoginTime(lastLoginTime)
                .build();
    }

    @Getter
    @Setter
    public static class UserIdOnly {
        private UUID userId;
    }
}