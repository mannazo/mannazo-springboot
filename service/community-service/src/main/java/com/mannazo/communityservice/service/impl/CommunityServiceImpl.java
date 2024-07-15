package com.mannazo.communityservice.service.impl;

import com.mannazo.communityservice.client.UserServiceClient;
import com.mannazo.communityservice.client.dto.UserResponseDTO;
import com.mannazo.communityservice.dto.request.CommunityRequestDTO;
import com.mannazo.communityservice.dto.response.CommunityResponseDTO;
import com.mannazo.communityservice.dto.response.CommunityWithUserDTO;
import com.mannazo.communityservice.eception.CommunityNotFoundException;
import com.mannazo.communityservice.entity.ImageEntity;
import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.entity.LikeEntity;
import com.mannazo.communityservice.mapStruct.CommunityRequestMapStruct;
import com.mannazo.communityservice.mapStruct.CommunityResponseMapStruct;
import com.mannazo.communityservice.repository.CommunityRepository;
import com.mannazo.communityservice.repository.LikeRepository;
import com.mannazo.communityservice.service.CommunityService;
import com.mannazo.communityservice.service.LikeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityResponseMapStruct communityResponseMapStruct;
    private final CommunityRequestMapStruct communityRequsetMapStruct;
    private final LikeRepository likeRepository;
    private final UserServiceClient userServiceClient;
    private final LikeService likeService;

    @Override
    public CommunityResponseDTO createCommunity(CommunityRequestDTO community) {
        CommunityEntity communityEntity = communityRequsetMapStruct.toEntity(community);

        // 이미지 URL을 ImageEntity로 변환하여 설정
        if (community.getImageUrls() != null && !community.getImageUrls().isEmpty()) {
            List<ImageEntity> images = community.getImageUrls().stream()
                    .map(url -> {
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setFilePath(url);
                        imageEntity.setCommunity(communityEntity); // 이미지와 게시물 간의 양방향 매핑 설정
                        return imageEntity;
                    })
                    .collect(Collectors.toList());
            communityEntity.setImages(images); // 게시물 엔티티에 이미지 엔티티 리스트 설정
        }

        CommunityEntity savedEntity = communityRepository.save(communityEntity);
        return communityResponseMapStruct.toResponseDTO(savedEntity);
    }

    @Override
    public CommunityWithUserDTO getCommunity(UUID communityId) {
        CommunityWithUserDTO communityWithUserDTO = new CommunityWithUserDTO();

        // 1. 커뮤니티 정보 조회
        CommunityEntity communityEntity = communityRepository.findById(communityId)
                .orElseThrow(() -> new CommunityNotFoundException("Community not found with id: " + communityId));

        // 2. 커뮤니티 정보 매핑
        CommunityResponseDTO communityResponseDTO = communityResponseMapStruct.toResponseDTO(communityEntity);
        communityWithUserDTO.setCommunity(communityResponseDTO);

        // 3. 사용자 정보 조회
        UUID userId = communityEntity.getUserId();
        UserResponseDTO userResponseDTO = userServiceClient.getUserById(userId).getBody();
        communityWithUserDTO.setUser(userResponseDTO);

        return communityWithUserDTO;
    }

    @Override
    public Page<CommunityWithUserDTO> findAll(Pageable pageable) {
        Page<CommunityEntity> communities = communityRepository.findAll(pageable);
        List<UUID> userIds = communities.stream()
                .map(CommunityEntity::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<UUID, UserResponseDTO> userMap = userServiceClient.getUsers(userIds);

        return communities.map(community -> {
            CommunityResponseDTO communityDTO = communityResponseMapStruct.toResponseDTO(community);
            UserResponseDTO user = userMap.get(community.getUserId());

            CommunityWithUserDTO compositeDTO = new CommunityWithUserDTO();
            compositeDTO.setCommunity(communityDTO);
            compositeDTO.setUser(user);
            return compositeDTO;
        });
    }

    @Override
    public void deleteCommunity(UUID communityId) {
        communityRepository.deleteById(communityId);
    }

    @Override
    public CommunityResponseDTO updateCommunity(UUID communityId, CommunityRequestDTO community) {
        CommunityEntity communityEntity = communityRepository.findById(communityId)
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + communityId));

        // 기존 이미지 유지
        List<ImageEntity> currentImages = communityEntity.getImages();

        // 이미지 URL을 ImageEntity로 변환하여 설정
        if (community.getImageUrls() != null && !community.getImageUrls().isEmpty()) {
            List<ImageEntity> images = community.getImageUrls().stream()
                    .map(url -> {
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setFilePath(url); // 이미지 URL을 filePath로 설정
                        imageEntity.setCommunity(communityEntity); // 이미지와 게시물 간의 양방향 매핑 설정
                        return imageEntity;
                    })
                    .collect(Collectors.toList());
            communityEntity.setImages(images); // 새로운 이미지 엔티티 리스트 설정
        } else {
            communityEntity.setImages(currentImages); // 이미지 변경 없으면 기존 이미지 유지
        }

        // 게시물 엔티티 업데이트
        communityRequsetMapStruct.updateCommunityFromDto(community, communityEntity);

        // lastUpdated 업데이트
        communityEntity.setLastUpdated(LocalDateTime.now());

        // 게시물 엔티티 저장
        CommunityEntity updatedEntity = communityRepository.save(communityEntity);

        return communityResponseMapStruct.toResponseDTO(updatedEntity);
    }

    @Override
    public List<UUID> findAllCommunityIds() {
        return communityRepository.findAllCommunityIds();
    }
}