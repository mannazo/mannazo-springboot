package com.mannazo.postservice.service.impl;

import com.mannazo.postservice.domain.PostRequestDTO;
import com.mannazo.postservice.domain.PostResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapStruct.PostRequestMapStruct;
import com.mannazo.postservice.mapStruct.PostResponseMapStruct;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostResponseMapStruct postResponseMapStruct;
    private final PostRequestMapStruct postRequsetMapStruct;

    @Override
    public PostResponseDTO createPost(PostRequestDTO post) {
        PostEntity postEntity = postRequsetMapStruct.toEntity(post);
        PostEntity savedEntity = postRepository.save(postEntity);
        return postResponseMapStruct.toPostResponseDTO(savedEntity);
    }

    @Override
    public PostResponseDTO getPost(UUID postId) {
        Optional<PostEntity> postEntity = postRepository.findById(postId);
        PostResponseDTO postInfo = postResponseMapStruct.toPostResponseDTO(postEntity.orElse(null));
        return postInfo;
    }

    @Override
    public List<PostResponseDTO> findAll() {

        List<PostEntity> list = postRepository.findAll();
        List<PostResponseDTO> postResponseDTOS = postResponseMapStruct.toPostResponseListDTO(list);

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
        postRequsetMapStruct.updatePostFromDto(post, postEntity);
        postRepository.save(postEntity);
        return postResponseMapStruct.toPostResponseDTO(postEntity);
    }
}
