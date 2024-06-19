package com.mannazo.mannazo.domain.travel.controller;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TravelPlanController {
    //게시물 조회
    @GetMapping("/travel/{id}")
    public void travel(@PathVariable UUID id) {
        return ;
    }

    //게시물 생성
    @PostMapping("/travel")
    public UserResponseDTO createTravel(@RequestBody UserRequestDTO travelinfo) {
        return null;
    }

    //게시물 수정
    @PutMapping("/travel")
    public UserResponseDTO updateTravel(@RequestBody UserRequestDTO travelinfo) {
        return null;
    }

    //게시물 삭제
    @PostMapping("/travel/{id}")
    public UserResponseDTO deleteTravel(@PathVariable UUID id) {
        return null;
    }
}
