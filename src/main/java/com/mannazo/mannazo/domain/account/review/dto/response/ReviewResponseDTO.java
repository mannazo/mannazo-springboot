package com.mannazo.mannazo.domain.account.review.dto.response;

import com.mannazo.mannazo.domain.account.review.dto.request.ReviewRequestDTO;
import com.mannazo.mannazo.domain.account.review.entity.ReviewEntity;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@ToString
@Builder
@Setter
public class ReviewResponseDTO {
    private UUID reviewId;
    private UUID tripId;
    private UUID reviewerId;
    private UUID revieweeId;
    private String content;
    private int rating;
    private Timestamp createAt;

    public static ReviewResponseDTO fromEntity(ReviewEntity reviewInfo) {
        return ReviewResponseDTO.builder()
                .reviewId(reviewInfo.getReviewId())
                .tripId(reviewInfo.getTripId())
                .reviewerId(reviewInfo.getReviewerId())
                .revieweeId(reviewInfo.getRevieweeId())
                .content(reviewInfo.getContent())
                .rating(reviewInfo.getRating())
                .createAt(reviewInfo.getCreateAt())
                .build();
    }

    @Builder
    @Getter
    public static class Delete {
        private UUID reviewId;
        private String messages;
    }

}
