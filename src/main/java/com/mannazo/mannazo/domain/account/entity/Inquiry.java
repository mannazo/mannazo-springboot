package com.mannazo.mannazo.domain.account.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inquiry_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID inquiryId; // 문의 고유 식별자

    @Column(name = "user_id", nullable = false)
    private UUID userId; // 사용자 식별자 (외래 키)

    @Column(name = "title", length = 255, nullable = false)
    private String title; // 문의 제목

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content; // 문의 내용

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InquiryStatus status; // 문의 상태 (Received, Processing, Completed)

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // 문의 타임스탬프

    // 연관 관계 매핑
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    public enum InquiryStatus {
        RECEIVED,
        PROCESSING,
        COMPLETED
    }
}


