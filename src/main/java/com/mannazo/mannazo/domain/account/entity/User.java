package com.mannazo.mannazo.domain.account.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
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

    @Column(name = "nickname", nullable = false)
    private String nickname; //  닉네임

    @Column(name = "nationality")
    private Integer nationality; // 사용자 국적 (선택 사항)

    @Column(name = "language")
    private String language; // 사용자 언어 (선택 사항)

    @Column(name = "profile_photo")
    private String profilePhoto; // 프로필 사진 URL (선택 사항)

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction; // 자기소개 (선택 사항)

    @Column(name = "city")
    private String city; // 사용자 거주지 (선택 사항)

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority = Authority.User; // 사용자 권한 (기본 값 User)

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender = Gender.선택안함; // 성별 (기본 값 선택안함)

    @Enumerated(EnumType.STRING)
    @Column(name = "mbti")
    private Mbti mbti = Mbti.선택안함; // Mbti (기본 값 선택안함)

    @Column(name = "interests", columnDefinition = "TEXT")
    private String interests; // 흥미 (선택 사항) 고정할거면 ENUM으로 변경해서 넣을 관심사 선정해야함

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "last_login_time")
    private Timestamp lastLoginTime; // 마지막 로그인 시간

    public enum Authority {
        Admin, User;
    }
    public enum Gender {
        선택안함, 남자, 여자;
    }
    public enum Mbti {
        선택안함, ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ;
    }
}
