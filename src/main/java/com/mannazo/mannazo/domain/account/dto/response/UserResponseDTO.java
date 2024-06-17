package com.mannazo.mannazo.domain.account.dto.response;

import com.mannazo.mannazo.domain.account.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
public class UserResponseDTO {
    private UUID userId;
    private String email;
    private String name;
    private String nickname;
    private Integer nationality;
    private String language;
    private String profileImage;
    private String introduction;
    private String city;
    private String gender;
    private String mbti;
    private String interests;
    private Timestamp lastLoginAt;

    public static UserResponseDTO fromEntity(User user) {
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
                .gender(user.getGender().name())
                .mbti(user.getMbti().name())
                .interests(user.getInterests())
                .lastLoginAt(user.getLastLoginTime())
                .build();
    }
}