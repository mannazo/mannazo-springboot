package com.mannazo.reviewservice.service;

import com.mannazo.reviewservice.dto.ReviewRequestDTO;
import com.mannazo.reviewservice.dto.ReviewResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    ReviewResponseDTO getReview(UUID reviewId);

    List<ReviewResponseDTO> findAll();

    ReviewResponseDTO createReview(ReviewRequestDTO review);

    void deleteReview(UUID reviewId);

    ReviewResponseDTO updateReview(UUID reviewId, ReviewRequestDTO review);

    List<ReviewResponseDTO> getReviewsByUser(UUID userId);

    double getAverageRating(UUID userId);
}
