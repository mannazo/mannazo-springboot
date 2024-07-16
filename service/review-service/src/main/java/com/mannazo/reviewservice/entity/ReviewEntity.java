package com.mannazo.reviewservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Review")
public class ReviewEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id", nullable = false, unique = true)
    private UUID reviewId;

    @Column(name = "session_id", nullable = false)
    private UUID sessionId;

    @Column(name = "reviewer_id", nullable = false)
    private UUID reviewerId;

    @Column(name = "reviewee_id", nullable = false)
    private UUID revieweeId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(); // 현재 시간으로 기본값 설정
        }
    }

}
