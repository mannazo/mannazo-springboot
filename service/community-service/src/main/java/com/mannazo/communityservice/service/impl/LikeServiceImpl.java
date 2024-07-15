package com.mannazo.communityservice.service.impl;

import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.entity.LikeEntity;
import com.mannazo.communityservice.repository.CommunityRepository;
import com.mannazo.communityservice.repository.LikeRepository;
import com.mannazo.communityservice.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final CommunityRepository communityRepository;
    private final RedissonClient redissonClient;
    private static final String LIKE_KEY_PREFIX = "like:";


    @Override
    @Transactional
    public void likeCommunity(UUID communityId, UUID userId) {
        CommunityEntity community = communityRepository.findById(communityId)
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + communityId));

        // Redisson 분산 락 획득
        RLock lock = redissonClient.getLock("likeLock:" + communityId);
        try {
            lock.lock(); // 분산 락 획득

            // 이미 좋아요를 누른 사용자인지 확인
            boolean alreadyLiked = likeRepository.existsByCommunityAndUserId(community, userId);
            if (alreadyLiked) {
                throw new IllegalStateException("User has already liked this community");
            }

            LikeEntity like = new LikeEntity();
            like.setCommunity(community);
            like.setUserId(userId);
            likeRepository.save(like);

            // Redis에 좋아요 정보 추가
            String key = LIKE_KEY_PREFIX + communityId;
            redissonClient.getSet(key).addAsync(userId.toString());
            log.info("뭐가들어가는거임?>>>>>>"+redissonClient.getSet(key));
            // likeCount 증가
            community.setLikeCount(community.getLikeCount() + 1);
            communityRepository.save(community);
        } finally {
            lock.unlock(); // 분산 락 해제
        }
    }

    @Override
    @Transactional
    public void unlikeCommunity(UUID communityId, UUID userId) {
        CommunityEntity community = communityRepository.findById(communityId)
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + communityId));

        // Redisson 분산 락 획득
        RLock lock = redissonClient.getLock("likeLock:" + communityId);
        try {
            lock.lock(); // 분산 락 획득

            LikeEntity like = likeRepository.findByCommunityAndUserId(community, userId)
                    .orElseThrow(() -> new EntityNotFoundException("Like not found for this community and user"));

            likeRepository.delete(like);

            // Redis에서 좋아요 정보 삭제
            String key = LIKE_KEY_PREFIX + communityId;
            redissonClient.getSet(key).removeAsync(userId.toString());
            // 비동기로 Redis Set에서 좋아요 정보 삭제

            // likeCount 감소
            community.setLikeCount(community.getLikeCount() - 1);
            communityRepository.save(community);
        } finally {
            lock.unlock(); // 분산 락 해제
        }
    }

    @Override
    public int getLikeCount(UUID communityId) {
        String key = LIKE_KEY_PREFIX + communityId;
        return redissonClient.getSet(key).size();
    }

    @Override
    public Long getLikesCount(UUID communityId) {
        String key = LIKE_KEY_PREFIX + communityId;
        Set<Object> members = redissonClient.getSet(key).readAll();
        return (long) members.size();
    }

    @Override
    public boolean isLiked(UUID communityId, UUID userId) {
        String key = LIKE_KEY_PREFIX + communityId;
        return redissonClient.getSet(key).contains(userId.toString());
    }

    @Override
    public void syncLikesFromDB(UUID communityId) {
        String key = LIKE_KEY_PREFIX + communityId;
        Set<UUID> userIds = likeRepository.findAllByCommunity_CommunityId(communityId)
                .stream()
                .map(LikeEntity::getUserId)
                .collect(Collectors.toSet());

        // Redisson 분산 락 획득
        RLock lock = redissonClient.getLock("syncLikesLock:" + communityId);
        try {
            lock.lock(); // 분산 락 획득

            // 기존 정보 삭제 후 새로 추가
            redissonClient.getSet(key).deleteAsync();
            redissonClient.getSet(key).addAllAsync(userIds);
        } finally {
            lock.unlock(); // 분산 락 해제
        }
    }
}
