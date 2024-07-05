package com.mannazo.postservice.service;

import com.mannazo.postservice.domain.PostRequestDTO;
import com.mannazo.postservice.domain.PostResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDTO getPost(UUID tripId);

    List<PostResponseDTO> findALL();

    void addPost(PostRequestDTO trip);

    void deletePost(UUID tripId);

    void updatePost(UUID tripId, PostRequestDTO trip);
}