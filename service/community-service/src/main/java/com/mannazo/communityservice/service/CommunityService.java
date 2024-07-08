package com.mannazo.communityservice.service;

import com.mannazo.communityservice.dto.CommunityRequestDTO;
import com.mannazo.communityservice.dto.CommunityResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CommunityService {
    CommunityResponseDTO getCommunity(UUID communityId);

    List<CommunityResponseDTO> findAll();

    CommunityResponseDTO createCommunity(CommunityRequestDTO community);

    void deleteCommunity(UUID communityId);

    CommunityResponseDTO updateCommunity(UUID communityId, CommunityRequestDTO community);
}
