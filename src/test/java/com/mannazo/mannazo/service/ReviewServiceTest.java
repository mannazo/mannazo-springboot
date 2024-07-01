package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.account.review.dto.request.ReviewRequestDTO;
import com.mannazo.mannazo.domain.account.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Slf4j
@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void testSaveReview() {
        ReviewRequestDTO review = ReviewRequestDTO.builder()
                .reviewId(UUID.randomUUID())
                .tripId(UUID.randomUUID())
                .content("추가리뷰")
                .rating(5)
                .reviewerId(UUID.randomUUID())
                .revieweeId(UUID.randomUUID())
                .build();

        reviewService.saveReview(review);
    }

}
