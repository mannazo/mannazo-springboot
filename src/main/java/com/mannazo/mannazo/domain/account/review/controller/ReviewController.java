package com.mannazo.mannazo.domain.account.review.controller;

import com.mannazo.mannazo.domain.account.review.dto.request.ReviewRequestDTO;
import com.mannazo.mannazo.domain.account.review.dto.response.ReviewResponseDTO;
import com.mannazo.mannazo.domain.account.review.service.ReviewService;
import com.mannazo.mannazo.domain.account.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseDTO reviewResponseDTO = reviewService.saveReview(reviewRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponseDTO);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable UUID reviewId, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        reviewRequestDTO.setReviewId(reviewId);
        ReviewResponseDTO reviewResponseDTO = reviewService.saveReview(reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO.Delete> deleteReview(@PathVariable UUID reviewId) {
        ReviewResponseDTO.Delete deleteResponse = reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(deleteResponse);
    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReview(@PathVariable UUID reviewId) {
        ReviewResponseDTO reviewResponseDTO = reviewService.getReview(reviewId);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(@PathVariable UUID userId) {
        List<ReviewResponseDTO> reviewResponseDTOs = reviewService.getReviews(userId);
        return ResponseEntity.ok(reviewResponseDTOs);
    }

}
