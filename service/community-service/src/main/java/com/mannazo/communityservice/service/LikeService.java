package com.mannazo.communityservice.service;

import java.util.Set;
import java.util.UUID;

public interface LikeService {
    void likeCommunity(UUID communityId, UUID userId);
    void unlikeCommunity(UUID communityId, UUID userId);
    int getLikeCount(UUID communityId);
    Long getLikesCount(UUID communityId);
    boolean isLiked(UUID communityId, UUID userId);
    void syncLikesFromDB(UUID communityId);

}
