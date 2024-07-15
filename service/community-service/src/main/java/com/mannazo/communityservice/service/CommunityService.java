package com.mannazo.communityservice.service;

import com.mannazo.communityservice.dto.request.CommunityRequestDTO;
import com.mannazo.communityservice.dto.response.CommunityResponseDTO;
import com.mannazo.communityservice.dto.response.CommunityWithUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommunityService {
    CommunityWithUserDTO getCommunity(UUID communityId);

    CommunityResponseDTO createCommunity(CommunityRequestDTO community);

    Page<CommunityWithUserDTO> findAll(Pageable pageable);

    void deleteCommunity(UUID communityId);

    CommunityResponseDTO updateCommunity(UUID communityId, CommunityRequestDTO community);

    List<UUID> findAllCommunityIds();
}
