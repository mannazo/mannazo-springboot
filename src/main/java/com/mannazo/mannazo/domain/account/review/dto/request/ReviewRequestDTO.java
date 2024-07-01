package com.mannazo.mannazo.domain.account.review.dto.request;

import com.mannazo.mannazo.domain.account.review.entity.ReviewEntity;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReviewRequestDTO {
    private UUID reviewId;
    private UUID tripId;
    private UUID reviewerId;
    private UUID revieweeId;
    private String content;
    private int rating;
    private Timestamp createAt;

    public ReviewEntity toEntity() {
        return ReviewEntity.builder()
                .reviewId(reviewId)
                .tripId(tripId)
                .reviewerId(reviewerId)
                .revieweeId(revieweeId)
                .content(content)
                .rating(rating)
                .build();
    }
}
