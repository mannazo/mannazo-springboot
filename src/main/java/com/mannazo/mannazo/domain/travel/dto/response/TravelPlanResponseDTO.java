package com.mannazo.mannazo.domain.travel.dto.response;

import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@ToString
public class TravelPlanResponseDTO {
    private UUID tripId;
    private UUID userId;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp createAt;
    private String interests;

    public static TravelPlanResponseDTO fromEntity(TravelPlanEntity travelInfo) {
        return TravelPlanResponseDTO.builder()
                .tripId(travelInfo.getTripId())
                .userId(travelInfo.getUserId())
                .startDate(travelInfo.getStartDate())
                .endDate(travelInfo.getEndDate())
                .createAt(travelInfo.getCreateAt())
                .interests(travelInfo.getInterests())
                .build();
    }
}
