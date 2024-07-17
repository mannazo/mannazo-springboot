package com.mannazo.reviewservice.controller;

import com.mannazo.reviewservice.dto.ReviewRequestDTO;
import com.mannazo.reviewservice.dto.ReviewResponseDTO;
import com.mannazo.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<String> hello() {
        String text = "Hello Review-Service";
        return ResponseEntity.status(HttpStatus.OK).body(text);
    }

    @PostMapping("")
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO review) {
        ReviewResponseDTO createdReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReview(@PathVariable UUID reviewId) {
        ReviewResponseDTO review = reviewService.getReview(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ReviewResponseDTO>> findAll() {
        List<ReviewResponseDTO> reviews = reviewService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable UUID reviewId, @RequestBody ReviewRequestDTO review) {
        ReviewResponseDTO updateReview = reviewService.updateReview(reviewId, review);
        return ResponseEntity.status(HttpStatus.OK).body(updateReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
        String text = reviewId+"가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }

    @GetMapping("/reviewer/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(@PathVariable UUID userId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping("/reviewee/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getRecievedReviewsByUser(@PathVariable UUID userId) {
        List<ReviewResponseDTO> reviews = reviewService.getRecievedReviewsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> getAverageRating(UUID userId) {
        double averageRating = reviewService.getAverageRating(userId);
        return ResponseEntity.status(HttpStatus.OK).body(averageRating);
    }
}
