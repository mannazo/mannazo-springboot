package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {
    //주어진 communityId를 가진 커뮤니티에 속한 좋아요(like) 엔티티의 개수를 조회
    int countByCommunityCommunityId(UUID communityId);
    //주어진 communityId와 userId를 가진 좋아요 엔티티가 존재하는지 여부를 확인
    boolean existsByCommunityCommunityIdAndUserId(UUID communityId, UUID userId);
    //주어진 communityId와 userId를 가진 좋아요 엔티티를 삭제
    void deleteByCommunityCommunityIdAndUserId(UUID communityId, UUID userId);
}
