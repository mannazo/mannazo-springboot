package com.mannazo.mannazo.domain.account.review.repository;

import com.mannazo.mannazo.domain.account.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    List<ReviewEntity> findByRevieweeId(UUID revieweeId);
}
