package com.mannazo.mannazo.domain.travel.entitiy;

import com.mannazo.mannazo.domain.account.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID reviewId; // 리뷰 고유 식별자

    @Column(name = "trip_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID tripId; // 여행 계획 식별자

    @Column(name = "reviewer_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID reviewerId; // 리뷰 작성자 식별자

    @Column(name = "reviewee_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID revieweeId; // 리뷰 대상자 식별자

    @Column(name = "rating", nullable = false)
    private int rating; // 평점

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content; // 리뷰 내용

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // 리뷰 타임스탬프
}
