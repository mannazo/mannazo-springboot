package com.mannazo.mannazo.domain.travel.controller;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.domain.travel.service.TravelPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TravelPlanController {

    private final TravelPlanService travelPlanService;
    //게시물 조회
    @GetMapping("/travel/{id}")
    public ResponseEntity<TravelPlanResponseDTO> gettravelInfo(@PathVariable UUID id) {
        TravelPlanResponseDTO response = travelPlanService.getTravelPlan(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //게시물 생성
    @PostMapping("/travel")
    public ResponseEntity<TravelPlanResponseDTO> createTravel(@RequestBody TravelPlanRequestDTO travelPlanRequestDTO) {
        TravelPlanResponseDTO response = travelPlanService.registerTravelPlan(travelPlanRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //게시물 수정
    @PutMapping("/travel")
    public ResponseEntity<TravelPlanResponseDTO> updateTravel(@RequestBody TravelPlanRequestDTO travelPlanRequestDTO) {
        TravelPlanResponseDTO response = travelPlanService.updateTravelPlan(travelPlanRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    //게시물 삭제
    @DeleteMapping("/travel/{id}")
    public ResponseEntity<TravelPlanResponseDTO.Delete> deleteTravel(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(travelPlanService.deleteTravelPlan(id));
    }
}
