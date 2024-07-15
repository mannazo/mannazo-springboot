package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.CommunityEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class CommunityCumstomRepositoryImpl implements CommunityCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<CommunityEntity> findAllByUserId(UUID userId) {
        return List.of();
    }
}
