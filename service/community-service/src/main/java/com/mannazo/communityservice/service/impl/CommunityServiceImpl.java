package com.mannazo.communityservice.service.impl;

import com.mannazo.communityservice.client.UserServiceClient;
import com.mannazo.communityservice.dto.CommunityRequestDTO;
import com.mannazo.communityservice.dto.CommunityResponseDTO;
import com.mannazo.communityservice.entity.ImageEntity;
import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.mapStruct.CommunityRequestMapStruct;
import com.mannazo.communityservice.mapStruct.CommunityResponseMapStruct;
import com.mannazo.communityservice.repository.CommunityRepository;
import com.mannazo.communityservice.service.CommunityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityResponseMapStruct communityResponseMapStruct;
    private final CommunityRequestMapStruct communityRequsetMapStruct;
    private final UserServiceClient userServiceClient;

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
        return communityResponseMapStruct.toCommunityResponseDTO(savedEntity);
    }

    @Override
    public CommunityResponseDTO getCommunity(UUID communityId) {
        Optional<CommunityEntity> communityEntity = communityRepository.findById(communityId);
        CommunityResponseDTO communityInfo = communityResponseMapStruct.toCommunityResponseDTO(communityEntity.orElse(null));
        return communityInfo;
    }

    @Override
    public List<CommunityResponseDTO> findAll() {

        List<CommunityEntity> list = communityRepository.findAll();
        List<CommunityResponseDTO> communityResponseDTOS = communityResponseMapStruct.toCommunityResponseListDTO(list);

        return communityResponseDTOS;
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

        // 게시물 엔티티 저장
        CommunityEntity updatedEntity = communityRepository.save(communityEntity);

        communityRequsetMapStruct.updateCommunityFromDto(community, communityEntity);

        communityRepository.save(communityEntity);
        return communityResponseMapStruct.toCommunityResponseDTO(communityEntity);
    }
}
