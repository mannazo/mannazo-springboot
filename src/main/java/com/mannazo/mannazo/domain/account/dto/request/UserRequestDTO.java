package com.mannazo.mannazo.domain.account.dto.request;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserRequestDTO {
    private UUID userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String nationality;
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


    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(UUID.randomUUID())
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .nationality(nationality)
                .language(language)
                .profilePhoto(profilePhoto)
                .introduction(introduction)
                .city(city)
                .authority("User")
                .gender(gender)
                .mbti(mbti)
                .interests(interests)
                .birthday(birthday)
                .lastLoginTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Getter
    @Setter
    public static class UserIdOnly {
        private UUID userId;
    }

    @Getter
    @Setter
    @ToString
    public static class Login {
        private String email;
        private String passsword;

        public UserEntity toEntity(){
            return UserEntity.builder()
                    .email(email)
                    .password(passsword)
                    .build();
        }
    }
}