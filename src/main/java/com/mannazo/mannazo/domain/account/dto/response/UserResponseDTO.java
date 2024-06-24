package com.mannazo.mannazo.domain.account.dto.response;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@ToString
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
    private String gender;
    private String mbti;
    private String interests;
    private String authority;
    private Timestamp lastLoginAt;


    public static UserResponseDTO fromEntity(UserEntity user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .nationality(user.getNationality())
                .language(user.getLanguage())
                .profileImage(user.getProfilePhoto())
                .introduction(user.getIntroduction())
                .city(user.getCity())
                .gender(user.getGender())
                .mbti(user.getMbti())
                .interests(user.getInterests())
                .lastLoginAt(user.getLastLoginTime())
                .build();
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