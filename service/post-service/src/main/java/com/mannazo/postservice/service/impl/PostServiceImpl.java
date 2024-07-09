package com.mannazo.postservice.service.impl;

import com.mannazo.postservice.client.UserServiceClient;
import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.ImageEntity;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapStruct.post.PostRequestMapStruct;
import com.mannazo.postservice.mapStruct.post.PostResponseMapStruct;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<PostResponseDTO> findAll() {

        List<PostEntity> list = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOS = postResponseMapStruct.toResponseListDTO(list);

        return postResponseDTOS;
    }

    @Override
    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostResponseDTO updatePost(UUID postId, PostRequestDTO post) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        // 기존 이미지 유지
        List<ImageEntity> currentImages = postEntity.getImages();

        // 이미지 URL을 ImageEntity로 변환하여 설정
        if (post.getImageUrls() != null && !post.getImageUrls().isEmpty()) {
            List<ImageEntity> images = post.getImageUrls().stream()
                    .map(url -> {
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setFilePath(url); // 이미지 URL을 filePath로 설정
                        imageEntity.setPost(postEntity); // 이미지와 게시물 간의 양방향 매핑 설정
                        return imageEntity;
                    })
                    .collect(Collectors.toList());
            postEntity.setImages(images); // 새로운 이미지 엔티티 리스트 설정
        } else {
            postEntity.setImages(currentImages); // 이미지 변경 없으면 기존 이미지 유지
        }

        // 게시물 엔티티 업데이트
        postRequsetMapStruct.updatePostFromDto(post, postEntity);

        // 게시물 엔티티 저장
        PostEntity updatedEntity = postRepository.save(postEntity);

        postRequsetMapStruct.updatePostFromDto(post, postEntity);

        postRepository.save(postEntity);
        return postResponseMapStruct.toResponseDTO(postEntity);
    }
}
