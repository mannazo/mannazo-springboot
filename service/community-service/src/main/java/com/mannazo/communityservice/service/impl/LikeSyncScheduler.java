package com.mannazo.communityservice.service.impl;

import com.mannazo.communityservice.service.CommunityService;
import com.mannazo.communityservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LikeSyncScheduler {
    private final LikeService likeService;
    private final CommunityService communityService;

    @Scheduled(cron = "0 0/10 * * * *")
    public void syncLikesTask() {
        List<UUID> communityIds = communityService.findAllCommunityIds();

        for (UUID communityId : communityIds) {
            likeService.syncLikesFromDB(communityId);
        }
    }

}
