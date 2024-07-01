package com.mannazo.mannazo.domain.account.review.service;

import com.mannazo.mannazo.domain.account.review.dto.request.ReviewRequestDTO;
import com.mannazo.mannazo.domain.account.review.dto.response.ReviewResponseDTO;
import com.mannazo.mannazo.domain.account.review.entity.ReviewEntity;
import com.mannazo.mannazo.domain.account.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO saveReview(ReviewRequestDTO reviewRequestDTO) {
        ReviewEntity reviewEntity = reviewRequestDTO.toEntity();
        ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
        return ReviewResponseDTO.fromEntity(savedEntity);
    }

    @Override
    public ReviewResponseDTO.Delete deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
        return ReviewResponseDTO.Delete.builder()
                .reviewId(reviewId)
                .messages("리뷰가 삭제되었습니다")
                .build();
    }

    @Override
    public ReviewResponseDTO getReview(UUID reviewId) {
        ReviewEntity getReview = reviewRepository.findById(reviewId).orElse(null);
        return ReviewResponseDTO.fromEntity(getReview);
    }

    @Override
    public List<ReviewResponseDTO> getReviews(UUID revieweeId) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByRevieweeId(revieweeId);
        return reviewEntities.stream()
                .map(ReviewResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
