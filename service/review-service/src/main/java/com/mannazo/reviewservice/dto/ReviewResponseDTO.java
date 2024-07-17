package com.mannazo.reviewservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class ReviewResponseDTO {
    private UUID reviewId;
    private UUID reviewerId;
    private UUID revieweeId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}