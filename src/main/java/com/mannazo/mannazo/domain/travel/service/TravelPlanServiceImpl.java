package com.mannazo.mannazo.domain.travel.service;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.domain.travel.repository.TravelPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelPlanServiceImpl implements TravelPlanService{

    private final TravelPlanRepository travelPlanRepository;

    @Override
    public TravelPlanResponseDTO getTravelPlan(UUID tripId) {
        TravelPlanEntity travelInfo = travelPlanRepository.findById(tripId).orElseThrow(() -> new NoSuchElementException("Travel plan not found"));
        return TravelPlanResponseDTO.fromEntity(travelInfo);
    }

    @Override
    public TravelPlanResponseDTO registerTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO) {
        TravelPlanEntity travelPlan = travelPlanRepository.save(travelPlanRequestDTO.toEntity());
        return TravelPlanResponseDTO.fromEntity(travelPlan);
    }

    @Override
    public TravelPlanResponseDTO updateTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO) {
        TravelPlanEntity updatedTravelPlan = travelPlanRepository.save(travelPlanRequestDTO.toEntity());
        return TravelPlanResponseDTO.fromEntity(updatedTravelPlan);
    }

    @Override
    public TravelPlanResponseDTO.Delete deleteTravelPlan(UUID tripId) {
        travelPlanRepository.deleteById(tripId);
        return TravelPlanResponseDTO.Delete.builder()
                .tripId(tripId)
                .messages("해당 게시물이 삭제되었습니다.")
                .build();
    }

}
