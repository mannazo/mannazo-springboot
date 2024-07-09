package com.mannazo.postservice.controller;

import com.mannazo.postservice.dto.CommentRequestDTO;
import com.mannazo.postservice.dto.CommentResponseDTO;
import com.mannazo.postservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO comment) {
        CommentResponseDTO createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> getComment(@PathVariable UUID commentId) {
        CommentResponseDTO comment = commentService.getComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @GetMapping("")
    public ResponseEntity<List<CommentResponseDTO>> findAll(UUID postId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable UUID commentId, @RequestBody CommentRequestDTO comment) {
        CommentResponseDTO updateComment = commentService.updateComment(commentId, comment);
        return ResponseEntity.status(HttpStatus.OK).body(updateComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        String text = commentId + "가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }
}
