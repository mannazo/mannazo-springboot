package com.mannazo.mannazo.api;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "여행 게시글 관련 API", description = "여행 게시글 CRUD를 위한 API")
public interface TravelAPI {

    @Operation(summary = "게시물 조회", description = "ID로 특정 여행 게시물을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID를 가진 여행 게시물이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
//    ResponseEntity<TravelPlanResponseDTO> gettravelInfo(@PathVariable UUID id);
    ResponseEntity<Map<String, Object>> getTravelInfo(@PathVariable UUID id);
    @Operation(summary = "게시물 생성", description = "새로운 여행 게시물을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    ResponseEntity<TravelPlanResponseDTO> createTravel(@RequestBody TravelPlanRequestDTO travelPlanRequestDTO);

    @Operation(summary = "게시물 수정", description = "기존의 여행 게시물을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "성공적으로 수정됨"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터"),
            @ApiResponse(responseCode = "404", description = "해당 ID를 가진 여행 게시물이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    ResponseEntity<TravelPlanResponseDTO> updateTravel(@RequestBody TravelPlanRequestDTO travelPlanRequestDTO);

    @Operation(summary = "게시물 삭제", description = "ID로 특정 여행 게시물을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID를 가진 여행 게시물이 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    ResponseEntity<TravelPlanResponseDTO.Delete> deleteTravel(@PathVariable UUID id);

    @Operation(summary = "전체 게시물 조회", description = "모든 여행 게시물을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    List<TravelPlanResponseDTO> findAllTravelPlan(Model model);

    @Operation(summary = "게시물 페이징 조회", description = "페이지 번호를 기준으로 여행 게시물을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    String paging(Pageable pageable, Model model);

    @Operation(summary = "언어로 사용자 필터링", description = "특정 언어를 사용하는 사용자를 필터링하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "404", description = "해당 언어를 사용하는 사용자가 존재하지 않음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    ResponseEntity<List<TravelPlanResponseDTO>> findUserByLanguage(@PathVariable String language);
}
