package com.mannazo.postservice.service;

import com.mannazo.postservice.dto.CommentRequestDTO;
import com.mannazo.postservice.dto.CommentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    CommentResponseDTO getComment(UUID commentId);

    List<CommentResponseDTO> getCommentsByPostId(UUID postId);

    CommentResponseDTO createComment(CommentRequestDTO comment);

    void deleteComment(UUID commentId);

    CommentResponseDTO updateComment(UUID commentId, CommentRequestDTO comment);

}
