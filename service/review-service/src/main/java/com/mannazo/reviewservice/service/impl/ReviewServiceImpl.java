package com.mannazo.reviewservice.service.impl;

import com.mannazo.reviewservice.dto.ReviewRequestDTO;
import com.mannazo.reviewservice.dto.ReviewResponseDTO;
import com.mannazo.reviewservice.entity.ReviewEntity;
import com.mannazo.reviewservice.mapStruct.RequestMapStruct;
import com.mannazo.reviewservice.mapStruct.ResponseMapStruct;
import com.mannazo.reviewservice.repository.ReviewRepository;
import com.mannazo.reviewservice.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ResponseMapStruct responseMapStruct;
    private final RequestMapStruct requestMapStruct;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO review) {
        ReviewEntity reviewEntity = requestMapStruct.toEntity(review);
        ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
        return responseMapStruct.toReviewResponseDTO(savedEntity);
    }

    @Override
    public ReviewResponseDTO getReview(UUID reviewId) {
        Optional<ReviewEntity> reviewEntity = reviewRepository.findById(reviewId);
        ReviewResponseDTO reviewInfo = responseMapStruct.toReviewResponseDTO(reviewEntity.orElse(null));
        return reviewInfo;
    }

    @Override
    public List<ReviewResponseDTO> findAll() {

        List<ReviewEntity> list = reviewRepository.findAll();
        List<ReviewResponseDTO> reviewResponseDTOS = responseMapStruct.toReviewResponseListDTO(list);

        return reviewResponseDTOS;
    }

    @Override
    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewResponseDTO updateReview(UUID reviewId, ReviewRequestDTO review) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + reviewId));
        requestMapStruct.updateEntityFromDto(review, reviewEntity);
        reviewRepository.save(reviewEntity);
        return responseMapStruct.toReviewResponseDTO(reviewEntity);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByUser(UUID userId) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByReviewerId(userId);
        return responseMapStruct.toReviewResponseListDTO(reviewEntities);
    }

    @Override
    public List<ReviewResponseDTO> getRecievedReviewsByUser(UUID userId) {
        List<ReviewEntity> recievedReviews = reviewRepository.findByRevieweeId(userId);
        return responseMapStruct.toReviewResponseListDTO(recievedReviews);
    }

    @Override
    public double getAverageRating(UUID userId) {
        return reviewRepository.getAverageRating(userId);
    }

}
