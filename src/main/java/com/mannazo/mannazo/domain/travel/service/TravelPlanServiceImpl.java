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
    public TravelPlanResponseDTO getTravelPlan(UUID travelPlanId) {
        TravelPlanEntity travelInfo = travelPlanRepository.findById(travelPlanId).orElseThrow(() -> new NoSuchElementException("Travel plan not found"));
        return TravelPlanResponseDTO.fromEntity(travelInfo);
    }

    @Override
    public TravelPlanResponseDTO registertravelPlan(TravelPlanRequestDTO travelPlanRequestDTO) {
        TravelPlanEntity travelPlan = travelPlanRepository.save(travelPlanRequestDTO.toEntity());
        return TravelPlanResponseDTO.fromEntity(travelPlan);
    }
}
