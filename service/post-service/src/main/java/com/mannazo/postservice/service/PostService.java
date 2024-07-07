package com.mannazo.postservice.service;

import com.mannazo.postservice.domain.PostRequestDTO;
import com.mannazo.postservice.domain.PostResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDTO getPost(UUID postId);

    List<PostResponseDTO> findAll();

    PostResponseDTO createPost(PostRequestDTO post);

    void deletePost(UUID postId);

    PostResponseDTO updatePost(UUID postId, PostRequestDTO post);
}
