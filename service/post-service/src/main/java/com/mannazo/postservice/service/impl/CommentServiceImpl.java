package com.mannazo.postservice.service.impl;

import com.mannazo.postservice.client.UserServiceClient;
import com.mannazo.postservice.dto.CommentRequestDTO;
import com.mannazo.postservice.dto.CommentResponseDTO;
import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.CommentEntity;
import com.mannazo.postservice.entity.ImageEntity;
import com.mannazo.postservice.entity.PostEntity;

import com.mannazo.postservice.mapStruct.commnet.CommentRequestMapStruct;
import com.mannazo.postservice.mapStruct.commnet.CommentResponseMapStruct;
import com.mannazo.postservice.repository.CommentRepository;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentResponseMapStruct commentResponseMapStruct;
    private final CommentRequestMapStruct commentRequsetMapStruct;
    private final UserServiceClient userServiceClient;
    private final PostRepository postRepository;

    @Override
    public CommentResponseDTO createComment(CommentRequestDTO comment) {
        // 1. CommentRequestDTO에서 postId 가져오기
        UUID postId = comment.getPostId();

        // 2. postId를 사용하여 PostEntity 조회
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        // 3. CommentEntity 생성 및 설정
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setPost(postEntity); // PostEntity 설정
        commentEntity.setUserId(comment.getUserId());
        commentEntity.setComment(comment.getComment());
        commentEntity.setCreateAt(new Timestamp(System.currentTimeMillis())); // 요청된 시간
        // 4. CommentEntity 저장
        CommentEntity savedEntity = commentRepository.save(commentEntity);

        // 5. CommentResponseDTO로 변환하여 반환
        return commentResponseMapStruct.toResponseDTO(savedEntity);
    }

    @Override
    public CommentResponseDTO getComment(UUID commentId) {
        Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);
        CommentResponseDTO commentInfo = commentResponseMapStruct.toResponseDTO(commentEntity.orElse(null));
        return commentInfo;
    }

    @Override
    public List<CommentResponseDTO> getCommentsByPostId(UUID postId) {
        List<CommentResponseDTO> list = commentRepository.getCommentsByPostId(postId);
        return list;
    }

    @Override
    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentResponseDTO updateComment(UUID commentId, CommentRequestDTO comment) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        // 게시물 엔티티 업데이트
        commentRequsetMapStruct.updateCommentFromDto(comment, commentEntity);

        // 게시물 엔티티 저장
        CommentEntity updatedEntity = commentRepository.save(commentEntity);

        commentRequsetMapStruct.updateCommentFromDto(comment, commentEntity);
        return commentResponseMapStruct.toResponseDTO(updatedEntity);
    }

}
