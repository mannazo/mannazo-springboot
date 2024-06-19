package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.service.TravelPlanService;
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
}

