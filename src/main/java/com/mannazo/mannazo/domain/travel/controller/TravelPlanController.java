package com.mannazo.mannazo.domain.travel.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;

import com.mannazo.mannazo.domain.travel.service.TravelPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "여행 게시글 관련 API")
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

    //게시물 전체 조회
    @GetMapping("/travel")
    public List<TravelPlanResponseDTO> findAllTravelPlan(Model model) {
        // DB에서 전체 게시글 데이터 가져오기
        return travelPlanService.findAll();
    }

    //게시물 페이징
    @GetMapping("/travel/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<TravelPlanResponseDTO> travelplanList = travelPlanService.paging(pageable);
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < travelplanList.getTotalPages()) ? startPage + blockLimit - 1 : travelplanList.getTotalPages();
        model.addAttribute("travelplanList", travelplanList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        //해당 페이지로 이동
        return "travelplanlist";
    }

    // 필터링 : language
    @GetMapping("/findUserByLanguage/{language}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findUserByLanguage(@PathVariable String language) {
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByLanguage(language);
        return ResponseEntity.ok(travelPlans);
    }
}
