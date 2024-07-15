package com.mannazo.communityservice.controller;

import com.mannazo.communityservice.dto.request.CommunityRequestDTO;
import com.mannazo.communityservice.dto.response.CommunityResponseDTO;
import com.mannazo.communityservice.dto.response.CommunityWithUserDTO;
import com.mannazo.communityservice.service.CommunityService;
import com.mannazo.communityservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final LikeService likeService;

    @GetMapping("/")
    public String community() {
        return "Hello Community-Service";
    }

    @PostMapping("")
    public ResponseEntity<CommunityResponseDTO> createCommunity(@RequestBody CommunityRequestDTO community) {
        CommunityResponseDTO createdCommunity = communityService.createCommunity(community);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommunity);
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<CommunityWithUserDTO> getCommunity(@PathVariable UUID communityId) {
        CommunityWithUserDTO community = communityService.getCommunity(communityId);
        return ResponseEntity.status(HttpStatus.OK).body(community);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<CommunityWithUserDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());
        Page<CommunityWithUserDTO> communitys = communityService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(communitys);
    }

    @PutMapping("/{communityId}")
    public ResponseEntity<CommunityResponseDTO> updateCommunity(@PathVariable UUID communityId, @RequestBody CommunityRequestDTO community) {
        CommunityResponseDTO updateCommunity = communityService.updateCommunity(communityId, community);
        return ResponseEntity.status(HttpStatus.OK).body(updateCommunity);
    }

    @DeleteMapping("/{communityId}")
    public ResponseEntity<String> deleteCommunity(@PathVariable UUID communityId) {
        communityService.deleteCommunity(communityId);
        String text = communityId+"가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }

    @PostMapping("/{communityId}/like")
    public ResponseEntity<Void> likeCommunity(@PathVariable UUID communityId, @RequestParam UUID userId) {
        likeService.likeCommunity(communityId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{communityId}/like")
    public ResponseEntity<String> unlikeCommunity(@PathVariable UUID communityId, @RequestParam UUID userId) {
        likeService.unlikeCommunity(communityId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("좋아요 취소");
    }

    @GetMapping("/{communityId}/likes")
    public ResponseEntity<Integer> getLikesCount(@PathVariable UUID communityId) {
        Long likesCount = likeService.getLikesCount(communityId);
        return ResponseEntity.status(HttpStatus.OK).body(likesCount.intValue());
    }
}
