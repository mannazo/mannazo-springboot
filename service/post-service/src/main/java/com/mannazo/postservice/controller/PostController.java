package com.mannazo.postservice.controller;

import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequireArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO post) {
        PostResponseDTO createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable UUID postId) {
        PostResponseDTO post = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("")
    public ResponseEntity<List<PostResponseDTO>> findAll() {
        List<PostResponseDTO> posts = postService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable UUID postId, @RequestBody PostRequestDTO post) {
        PostResponseDTO updatePost = postService.updatePost(postId, post);
        return ResponseEntity.status(HttpStatus.OK).body(updatePost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);
        String text = postId+"가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }
}
