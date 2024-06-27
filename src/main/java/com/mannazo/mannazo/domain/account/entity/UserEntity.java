package com.mannazo.mannazo.domain.account.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId; // 사용자 고유 식별자

    @Column(name = "name")
    private String name; // 사용자 이름

    @Column(name = "email", unique = true)
    private String email; // 사용자 이메일

    @Column(name = "nickname")
    private String nickname; //  닉네임

    @Column(name = "age")
    private int age; // 사용자 나이

    @Column(name = "nationality")
    private String nationality; // 사용자 국적 (선택 사항)

    @Column(name = "language")
    private String language; // 사용자 언어 (선택 사항)

    @Column(name = "profile_photo")
    private String profilePhoto; // 프로필 사진 URL (선택 사항)

    @Column(name = "introduction")
    private String introduction; // 자기소개 (선택 사항)

    @Column(name = "city")
    private String city; // 사용자 거주지 (선택 사항)

    @Column(name = "gender")
    private String gender; // 성별 (기본 값 선택안함)

    @Column(name = "mbti")
    private String mbti; // Mbti (기본 값 선택안함)

    @Column(name = "interests")
    private String interests; // 흥미 (선택 사항)

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "social_login_id")
    private String socialLoginId;

    @Column(name = "last_login_time")
    private Timestamp lastLoginTime; // 마지막 로그인 시간
}
