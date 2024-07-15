package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {
    Optional<LikeEntity> findByCommunityAndUserId(CommunityEntity community, UUID userId);

    boolean existsByCommunityAndUserId(CommunityEntity community, UUID userId);

    List<LikeEntity> findAllByCommunity_CommunityId(UUID communityId);
}
