package com.mannazo.postservice.service;

import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDTO getPost(UUID postId);

    Page<PostResponseDTO> findAll(Pageable pageable);

    PostResponseDTO createPost(PostRequestDTO post);

    void deletePost(UUID postId);

    PostResponseDTO updatePost(UUID postId, PostRequestDTO post);

    int getNumberOfPosts();

    //검색 기능
    Page<PostResponseDTO> searchPosts(String travelCity, PreferredGender preferredGender, String travelStyle, Pageable pageable);
}
