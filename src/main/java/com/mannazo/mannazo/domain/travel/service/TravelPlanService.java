package com.mannazo.mannazo.domain.travel.service;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;

import java.util.UUID;

public interface TravelPlanService {
    //게시물 조회
    TravelPlanResponseDTO getTravelPlan(UUID travelPlanId);

    //게시물 등록
    TravelPlanResponseDTO registerTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO);


}
