package com.mannazo.mannazo.domain.account.dto.response;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
public class UserResponseDTO {

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
    private Timestamp lastLoginAt;
    private boolean firstTimeUser;


    public static UserResponseDTO fromEntity(UserEntity user) {
        String interests = user.getInterests();

        List<String> interestList = (interests == null || interests.trim().isEmpty())
                ? Collections.emptyList()
                : Arrays.asList(interests.substring(1, interests.length() - 1).split(", "));

        UserResponseDTO response = UserResponseDTO.builder()
                .userId(user.getUserId())
                .age(user.getAge())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .nationality(user.getNationality())
                .language(user.getLanguage())
                .profilePhoto(user.getProfilePhoto())
                .introduction(user.getIntroduction())
                .city(user.getCity())
                .gender(user.getGender())
                .mbti(user.getMbti())
                .interests(interestList)
                .birthday(user.getBirthday())
                .lastLoginAt(user.getLastLoginTime())
                .firstTimeUser(user.getName() == null || user.getName().trim().isEmpty())
                .build();

        response.setFirstTimeUser(response.getName() == null || response.getName().trim().isEmpty());

        return response;
    }

    @Builder
    @Getter
    public static class Delete {
        private UUID userId;
        private String messages;
    }
}