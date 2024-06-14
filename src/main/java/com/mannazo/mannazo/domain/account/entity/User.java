package com.mannazo.mannazo.domain.account.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId; // 사용자 고유 식별자

    @Column(name = "email", nullable = false, unique = true)
    private String email; // 사용자 이메일

    @Column(name = "password", nullable = false)
    private String password; // 사용자 비밀번호

    @Column(name = "name", nullable = false)
    private String name; // 사용자 이름

    @Column(name = "nationality")
    private Integer nationality; // 사용자 국적 (선택 사항)

    @Column(name = "language")
    private String language; // 사용자 언어 (선택 사항)

    @Column(name = "profile_photo")
    private String profilePhoto; // 프로필 사진 URL (선택 사항)

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio; // 자기소개 (선택 사항)

    @Column(name = "residence")
    private String residence; // 사용자 거주지 (선택 사항)

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.User; // 사용자 권한 (기본 값 User)

    @Column(name = "last_login_time")
    private java.sql.Timestamp lastLoginTime; // 마지막 로그인 시간

    public enum Role {
        Admin, User;
    }
}
