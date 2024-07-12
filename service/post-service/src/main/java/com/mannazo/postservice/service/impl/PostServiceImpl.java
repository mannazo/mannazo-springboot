package com.mannazo.postservice.service.impl;

import com.mannazo.postservice.client.UserServiceClient;
import com.mannazo.postservice.client.dto.UserResponseDTO;
import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.dto.PostWithUserResponseDTO;
import com.mannazo.postservice.entity.ImageEntity;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import com.mannazo.postservice.mapStruct.post.PostRequestMapStruct;
import com.mannazo.postservice.mapStruct.post.PostResponseMapStruct;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.repository.specification.PostSpecification;
import com.mannazo.postservice.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostResponseMapStruct postResponseMapStruct;
    private final PostRequestMapStruct postRequsetMapStruct;
    private final UserServiceClient userServiceClient;

    @Override
    public PostResponseDTO createPost(PostRequestDTO post) {
        PostEntity postEntity = postRequsetMapStruct.toEntity(post);

        // 이미지 URL을 ImageEntity로 변환하여 설정
        if (post.getImageUrls() != null && !post.getImageUrls().isEmpty()) {
            List<ImageEntity> images = post.getImageUrls().stream()
                    .map(url -> {
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setFilePath(url);
                        imageEntity.setPost(postEntity); // 이미지와 게시물 간의 양방향 매핑 설정
                        return imageEntity;
                    })
                    .collect(Collectors.toList());
            postEntity.setImages(images); // 게시물 엔티티에 이미지 엔티티 리스트 설정
        }

        PostEntity savedEntity = postRepository.save(postEntity);
        return postResponseMapStruct.toResponseDTO(savedEntity);
    }

    @Override
    public PostResponseDTO getPost(UUID postId) {
        Optional<PostEntity> postEntity = postRepository.findById(postId);
        PostResponseDTO postInfo = postResponseMapStruct.toResponseDTO(postEntity.orElse(null));
        return postInfo;
    }

    @Override
    public PostResponseDTO updatePost(UUID postId, PostRequestDTO post) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        // 기존 이미지 유지
        List<ImageEntity> currentImages = postEntity.getImages();

        // 이미지 URL을 ImageEntity로 변환하여 설정
        if (post.getImageUrls() != null && !post.getImageUrls().isEmpty()) {
            List<ImageEntity> updatedImages = post.getImageUrls().stream()
                    .map(url -> {
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setFilePath(url);
                        imageEntity.setPost(postEntity); // 이미지와 게시물 간의 양방향 매핑 설정
                        return imageEntity;
                    })
                    .collect(Collectors.toList());

            // 기존 이미지 컬렉션과 새로운 이미지 컬렉션을 병합
            currentImages.clear(); // 기존 이미지 삭제
            currentImages.addAll(updatedImages); // 새로운 이미지 추가
        }

        // 게시물 엔티티 업데이트
        postRequsetMapStruct.updatePostFromDto(post, postEntity);

        // 게시물 엔티티 저장
        PostEntity updatedEntity = postRepository.save(postEntity);

        return postResponseMapStruct.toResponseDTO(updatedEntity);
    }

    @Override
    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }


    @Override
    public int getNumberOfPosts() {
        List<PostEntity> list = postRepository.findAll();
        return list.size();
    }

    @Override
    public Page<PostWithUserResponseDTO> findAll(Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAll(pageable);

        // Fetch users for all posts in one go
        List<UUID> userIds = posts.stream()
                .map(PostEntity::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<UUID, UserResponseDTO> usersMap = userServiceClient.getUsers(userIds);

        return posts.map(post -> {
            PostResponseDTO postDTO = postResponseMapStruct.toResponseDTO(post);
            UserResponseDTO userDTO = usersMap.get(post.getUserId());

            PostWithUserResponseDTO compositeDTO = new PostWithUserResponseDTO();
            compositeDTO.setPost(postDTO);
            compositeDTO.setUser(userDTO);

            return compositeDTO;
        });
    }

    @Override
    public Page<PostWithUserResponseDTO> searchPosts(String travelCity, PreferredGender preferredGender, String[] travelStyles, String travelStatus, LocalDate startDate, LocalDate endDate, String[] travelNationalities, Pageable pageable) {

        Specification<PostEntity> spec = Specification.where(null);

        if (travelCity != null && !travelCity.isEmpty()) {
            spec = spec.and(PostSpecification.hasTravelCity(travelCity));
        }

        if (preferredGender != null) {
            spec = spec.and(PostSpecification.hasPreferredGender(preferredGender));
        }

        if (travelStyles != null && travelStyles.length > 0) {
            spec = spec.and(PostSpecification.hasTravelStyles(travelStyles));
        }

        if (travelStatus != null && !travelStatus.isEmpty()) {
            spec = spec.and(PostSpecification.hasTravelStatus(travelStatus));
        }

        if (startDate != null || endDate != null) {
            spec = spec.and(PostSpecification.betweenDates(startDate, endDate));
        }

        if (travelNationalities != null && travelNationalities.length > 0) {
            spec = spec.and(PostSpecification.hasTravelNationalities(travelNationalities));
        }

        Page<PostEntity> posts = postRepository.findAll(spec, pageable);

        // Fetch users for all posts in one go
        List<UUID> userIds = posts.stream()
                .map(PostEntity::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<UUID, UserResponseDTO> usersMap = userServiceClient.getUsers(userIds);


        return posts.map(post -> {
            PostResponseDTO postDTO = postResponseMapStruct.toResponseDTO(post);
            UserResponseDTO userDTO = usersMap.get(post.getUserId());

            PostWithUserResponseDTO compositeDTO = new PostWithUserResponseDTO();
            compositeDTO.setPost(postDTO);
            compositeDTO.setUser(userDTO);

            return compositeDTO;
        });
    }
}

