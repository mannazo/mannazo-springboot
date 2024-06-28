package com.mannazo.mannazo.domain.travel.service;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TravelPlanService {

    /**
     * tripId로 특정 게시물 조회
     * @param tripId
     * @return TravelPlanResponseDTO tripId로 검색된 여행계획 정보
     */
    TravelPlanResponseDTO getTravelPlan(UUID tripId);

    //게시물 등록
    /**
     * 여행계획 게시물 등록
     * @param travelPlanRequestDTO 작성한 여행계획
     * @return TravelPlanResponseDTO 등록된 여행계획
     */
    TravelPlanResponseDTO registerTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO);

    //게시물 수정
    /**
     * 여행계획 게시물 수정
     * @param travelPlanRequestDTO 수정할 여행계획
     * @return TravelPlanResponseDTO 수정된 여행계획
     */
    TravelPlanResponseDTO updateTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO);

    /**
     * 등록된 여행계획 게시물 삭제
     * @param tripId 삭제할 여행계획 tripId
     * @return 삭제된 여행계획 tirpId와 삭제 메시지
     */
    TravelPlanResponseDTO.Delete deleteTravelPlan(UUID tripId);

    /**
     * 등록되어있는 전체 여행계획 게시물 조회
     * @return 여행계획 모든 정보 DTO 목록
     */
    List<TravelPlanResponseDTO> findAll();

    /**
     * 여행계획 게시물 페이지 단위로 조회
     * @param pageable 페이지 요청 정보를 담음, 페이지 번호, 페이지 크기, 정렬 정보
     * @return 페이지 단위로 분할 된 여행 계획 게시물의 DTO 목록
     */
    Page<TravelPlanResponseDTO> paging(Pageable pageable);

    /**
     * 주어진 언어를 사용하는 사용자가 작성한 여행계획 조회
     * @param language 필터링할 언어
     * @return 주어진 언어를 사용하는 사용자가 작성한 모든 여행 계획 정보를 담고 있는 DTO의 목록
     */
    List<TravelPlanResponseDTO> findByLanguage(String language);

    //필터링 : userId를 통한 게시물 조회
    List<TravelPlanResponseDTO> findByUserId(UUID userId);
    //필터링 : 성별
    List<TravelPlanResponseDTO> findByGender(String gender);
    //필터링 : 성향
    List<TravelPlanResponseDTO> findByInterests(String interests);
    //필터링 : 지정 날짜가 시작일자 - 끝일자 사이일 경우
    List<TravelPlanResponseDTO> findByDateBetween(LocalDate date);
}
