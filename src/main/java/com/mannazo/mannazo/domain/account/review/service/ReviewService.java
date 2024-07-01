package com.mannazo.mannazo.domain.account.review.service;

import com.mannazo.mannazo.domain.account.review.dto.request.ReviewRequestDTO;
import com.mannazo.mannazo.domain.account.review.dto.response.ReviewResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    /**
     * 리뷰를 저장합니다.
     * @param reviewRequestDTO 리뷰 저장 요청 DTO
     * @return 저장된 리뷰의 응답 DTO
     */
    ReviewResponseDTO saveReview(ReviewRequestDTO reviewRequestDTO);

    /**
     * 리뷰를 삭제합니다.
     * @param reviewId 삭제할 리뷰의 식별자 (UUID)
     * @return 삭제된 리뷰의 응답 DTO
     */
    ReviewResponseDTO.Delete deleteReview(UUID reviewId);

    /**
     * 특정 리뷰를 조회합니다.
     * @param reviewId 조회할 리뷰의 식별자 (UUID)
     * @return 조회된 리뷰의 응답 DTO
     */
    ReviewResponseDTO getReview(UUID reviewId);

    /**
     * 특정 리뷰 대상자의 모든 리뷰를 조회합니다.
     * @param revieweeId 리뷰 대상자의 식별자 (UUID)
     * @return 리뷰 대상자의 모든 리뷰를 포함한 응답 DTO 리스트
     */
    List<ReviewResponseDTO> getReviews(UUID revieweeId);
}
