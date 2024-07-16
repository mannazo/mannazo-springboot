package com.mannazo.reviewservice.repository;

import com.mannazo.reviewservice.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID>, ReviewCustom {

    List<ReviewEntity> findByReviewerId(UUID userId);

    @Override
    double getAverageRating(UUID userId);
}
