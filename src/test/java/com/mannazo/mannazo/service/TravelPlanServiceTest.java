package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.domain.travel.repository.TravelPlanRepository;
import com.mannazo.mannazo.domain.travel.service.TravelPlanService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class TravelPlanServiceTest {

    @Autowired
    private TravelPlanService travelPlanService;

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @Test
    public void 여행계획_테스트(){
        // Given
        UUID tripId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String destination = "서울";

        LocalDate startDate = LocalDate.of(2024, 7, 19);
        LocalDate endDate = startDate.plusDays(7); // 일주일 후Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp createAt = new Timestamp(System.currentTimeMillis());
        String interests = "음식, 문화";

        TravelPlanRequestDTO travelInfo = TravelPlanRequestDTO.builder()
                .tripId(tripId)
                .userId(userId)
                .destination(destination)
                .startDate(startDate)
                .endDate(endDate)
                .createAt(createAt)
                .interests(interests)
                .build();
        travelPlanService.registerTravelPlan(travelInfo);
    }

    @Test
    public void 여행계획_조회테스트() {
        UUID tripId = UUID.fromString("6f201bc7-aaeb-46b9-b0a3-f6ed8affba51");
        log.info(travelPlanService.getTravelPlan(tripId).toString());
    }

    @Test
    public void 여행계획_수정테스트() {
        //Given
        UUID tripId = UUID.fromString("ea312454-edaf-4d9d-923e-287ed1199bb7");

        TravelPlanRequestDTO dto = TravelPlanRequestDTO.builder()
                .tripId(tripId)
                .userId(UUID.randomUUID())
                .destination("목포")
                .startDate(LocalDate.of(2024, 7, 19))
                .endDate(LocalDate.of(2024, 7, 27))
                .createAt(new Timestamp(System.currentTimeMillis()))
                .interests("고궁")
                .build();


        //수정내용
        TravelPlanEntity updatedTravelPlanEntity = travelPlanRepository.save(dto.toEntity());

        //Then
        log.info("수정완료" +updatedTravelPlanEntity.toString());
    }

    @Test
    public void 여행계획_삭제테스트() {
        //Given
        UUID tripId = UUID.fromString("6f201bc7-aaeb-46b9-b0a3-f6ed8affba51");
        travelPlanService.deleteTravelPlan(tripId);
    }
}

