package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.CommunityEntity;

import java.util.List;
import java.util.UUID;

public interface CommunityCustomRepository {
    List<CommunityEntity> findAllByUserId(UUID userId);
}
