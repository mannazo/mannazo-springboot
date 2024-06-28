package com.mannazo.mannazo.domain.account.dto.request;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class UserRequestDTO {
    private UUID userId;
    private String email;
    private String name;
    private String nickname;
    private int age;
    private String nationality;
    private String language;
    private String profilePhoto;
    private String introduction;
    private String city;
    private String gender;
    private String mbti;
    private List<String> interests;
    private LocalDate birthday;
    private Timestamp lastLoginTime;

    public UserEntity toEntity() {
        int age = calculateAge(this.birthday);

        return UserEntity.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .nickname(nickname)
                .age(age)
                .nationality(nationality)
                .language(language)
                .profilePhoto("https://mannazo-images-bucket.s3.ap-northeast-2.amazonaws.com/" + profilePhoto)
                .introduction(introduction)
                .city(city)
                .gender(gender)
                .mbti(mbti)
                .interests(String.valueOf(interests))
                .birthday(birthday)
                .lastLoginTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    //나이계산
    private int calculateAge(LocalDate birthday) {
        LocalDate now = LocalDate.now();
        return Period.between(birthday, now).getYears();
    }

    @Getter
    @Setter
    public static class UserIdOnly {
        private UUID userId;
    }

}