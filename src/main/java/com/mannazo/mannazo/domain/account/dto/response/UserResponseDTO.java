package com.mannazo.mannazo.domain.account.dto.response;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

        // 대괄호 제거
//        String trimmedInterests = interests.substring(1, interests.length() - 1);

        // 문자열을 쉼표로 분리하여 리스트로 변환
//        List<String> interestList = Arrays.asList(trimmedInterests.split(", "));

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

    // Inner class for registration DTO
    @Builder
    @Getter
    public static class RegisterDTO {
        private String email;
        private String password;
        private String name;
        private String nickname;
        private String nationality;
        private String language;
        private String city;
        private String gender;
        private String mbti;
        private String interests;
    }

    @Builder
    @Getter
    public static class Delete {
        private UUID userId;
        private String messages;
    }
}