package com.mannazo.reviewservice.repository;


import com.mannazo.reviewservice.dto.ReviewResponseDTO;
import com.mannazo.reviewservice.entity.QReviewEntity;
import com.mannazo.reviewservice.mapStruct.ResponseMapStruct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class ReviewCustomImpl implements ReviewCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final ResponseMapStruct reviewResponseMapStruct;


    @Override
    public double getAverageRating(UUID userId) {
        QReviewEntity reviewEntity = QReviewEntity.reviewEntity;
        return jpaQueryFactory
                .select(reviewEntity.rating.avg())
                .from(reviewEntity)
                .fetchOne();
    }
}
