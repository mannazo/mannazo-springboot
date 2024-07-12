package com.mannazo.postservice.service;

import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.dto.PostWithUserResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponseDTO getPost(UUID postId);

    Page<PostWithUserResponseDTO> findAll(Pageable pageable);

    PostResponseDTO createPost(PostRequestDTO post);

    void deletePost(UUID postId);

    PostResponseDTO updatePost(UUID postId, PostRequestDTO post);

    int getNumberOfPosts();

    //검색 기능
    Page<PostWithUserResponseDTO> searchPosts(String travelCity, PreferredGender preferredGender, String[] travelStyles, String travelStatus, LocalDate startDate, LocalDate endDate, String[] travelNationalities, Pageable pageable);
}
