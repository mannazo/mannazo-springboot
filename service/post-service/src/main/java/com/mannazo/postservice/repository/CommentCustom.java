package com.mannazo.postservice.repository;

import com.mannazo.postservice.dto.CommentResponseDTO;
import com.mannazo.postservice.entity.CommentEntity;

import java.util.List;
import java.util.UUID;

public interface CommentCustom {
    List<CommentResponseDTO> getCommentsByPostId(UUID postId);
}
