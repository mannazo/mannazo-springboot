package com.mannazo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "language")
    private String language;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "city")
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "interests")
    private String interests;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "last_login_at")
    private Timestamp lastLoginAt;

    @Column(name = "created_at")
    private Timestamp createdAt;


    //기본값 설정
    @PrePersist
    protected void onCreate() {
        if (this.authority == null) {
            this.authority = Authority.User; // Authority 기본값 설정
        }
        if (this.lastLoginAt == null) {
            this.lastLoginAt = Timestamp.valueOf(LocalDateTime.now()); // 현재 시간으로 기본값 설정
        }
        if (this.createdAt == null) {
            this.createdAt = Timestamp.valueOf(LocalDateTime.now()); // 현재 시간으로 기본값 설정
        }
    }
}
