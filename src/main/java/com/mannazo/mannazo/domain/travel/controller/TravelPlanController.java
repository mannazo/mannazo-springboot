package com.mannazo.mannazo.domain.travel.controller;

import com.mannazo.mannazo.api.TravelAPI;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TravelPlanController implements TravelAPI {

    private final TravelPlanService travelPlanService;
    private final UserService userService;

    //게시물 조회
//    @GetMapping("/travel/{id}")
//    public ResponseEntity<TravelPlanResponseDTO> gettravelInfo(@PathVariable UUID id) {
//        TravelPlanResponseDTO response = travelPlanService.getTravelPlan(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/travel/{id}")
    public ResponseEntity<Map<String, Object>> getTravelInfo(@PathVariable UUID id) {
        TravelPlanResponseDTO travelPlanResponse = travelPlanService.getTravelPlan(id);
        UserResponseDTO userResponse = userService.retrieveUser(travelPlanResponse.getUserId());

        Map<String, Object> response = new HashMap<>();
        response.put("travelPlan", travelPlanResponse);
        response.put("user", userResponse);

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
    @GetMapping("/findByLanguage/{language}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findUserByLanguage(@PathVariable String language) {
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByLanguage(language);
        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/findByGender/{gender}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findUserByGender(@PathVariable String gender) {
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByGender(gender);
        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/findByInterests/{interests}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findUserByInterests(@PathVariable String interests) {
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByInterests(interests);
        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/findByTravelStyle/{travelStyle}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findByTravelStyle(@PathVariable String travelStyle) {
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByTravelStyle(travelStyle);
        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/findByDestination/{destination}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findByDestination(@PathVariable String destination) {
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByDestination(destination);
        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/findByDate/{date}")
    public ResponseEntity<List<TravelPlanResponseDTO>> findByDate(@PathVariable LocalDate date) {
        List<TravelPlanResponseDTO> travelPlans =travelPlanService.findByDateBetween(date);
        return ResponseEntity.ok(travelPlans);
    }
}
