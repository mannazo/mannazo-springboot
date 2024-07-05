package com.mannazo.postservice.controller;

import com.mannazo.postservice.domain.PostRequestDTO;
import com.mannazo.postservice.domain.PostResponseDTO;
import com.mannazo.postservice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{tripId}")
    public PostResponseDTO getPost(@PathVariable UUID tripId) {
        PostResponseDTO post = postService.getPost(tripId);
        return post;
    }

    @GetMapping("")
    public List<PostResponseDTO> findAll() {
        List<PostResponseDTO> post = postService.findALL();

        return post;
    }

    @PostMapping("")
    public UUID addPost(@RequestBody PostRequestDTO trip) {
        postService.addPost(trip);
        return trip.getTripId();
    }

    @DeleteMapping("/{tripId}")
    public String deletePost(@PathVariable UUID tripId) {
        postService.deletePost(tripId);
        return tripId+"가 삭제됨";
    }

    @PutMapping("/{tripId}")
    public String updatePost(@PathVariable UUID tripId, @RequestBody PostRequestDTO trip) {
        postService.updatePost(tripId, trip);
        return tripId+"가 업데이트 되었습니다.";
    }

}
