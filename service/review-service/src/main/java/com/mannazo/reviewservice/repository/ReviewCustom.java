package com.mannazo.reviewservice.repository;

import com.mannazo.reviewservice.dto.ReviewResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewCustom {
    double getAverageRating(UUID userId);
}
