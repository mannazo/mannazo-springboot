package com.mannazo.communityservice.controller;

import com.mannazo.communityservice.dto.CommunityRequestDTO;
import com.mannazo.communityservice.dto.CommunityResponseDTO;
import com.mannazo.communityservice.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("")
    public ResponseEntity<CommunityResponseDTO> createCommunity(@RequestBody CommunityRequestDTO community) {
        CommunityResponseDTO createdCommunity = communityService.createCommunity(community);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCommunity);
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<CommunityResponseDTO> getCommunity(@PathVariable UUID communityId) {
        CommunityResponseDTO community = communityService.getCommunity(communityId);
        return ResponseEntity.status(HttpStatus.OK).body(community);
    }

    @GetMapping("")
    public ResponseEntity<List<CommunityResponseDTO>> findAll() {
        List<CommunityResponseDTO> communitys = communityService.findAll();
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

}
