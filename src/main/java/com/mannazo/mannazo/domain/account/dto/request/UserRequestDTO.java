package com.mannazo.mannazo.domain.account.dto.request;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class UserRequestDTO {
    private UUID userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private int age;
    private String nationality;
    private String language;
    private String profilePhoto;
    private String introduction;
    private String city;
    private String authority;
    private String gender;
    private String mbti;
    private String interests;
    private LocalDate birthday;
    private Timestamp lastLoginTime;

    private String aws_s3_profile_url = "https://mannazo-images-bucket.s3.ap-northeast-2.amazonaws.com/";

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .nickname(nickname)
                .age(age)
                .nationality(nationality)
                .language(language)
                .profilePhoto(aws_s3_profile_url + profilePhoto)
                .introduction(introduction)
                .city(city)
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

}