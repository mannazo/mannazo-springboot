package com.mannazo.postservice.controller;

import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.dto.PostWithUserResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import com.mannazo.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String Post() {
        return "Hello Post-Service";
    }

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

    @GetMapping("/count")
    public int getNumberOfPosts() {
        return postService.getNumberOfPosts();
    }

    //페이지네이션 추가
    @GetMapping("/findAll")
    public ResponseEntity<Page<PostWithUserResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<PostWithUserResponseDTO> posts = postService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PostWithUserResponseDTO>> searchPosts(
            @RequestParam(required = false) String travelCity,
            @RequestParam(required = false) PreferredGender preferredGender,
            @RequestParam(required = false) String[] travelStyle,
            @RequestParam(required = false) String travelStatus,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String[] travelNationalities,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<PostWithUserResponseDTO> posts = postService.searchPosts(
                travelCity, preferredGender, travelStyle, travelStatus, startDate, endDate, travelNationalities, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
}
