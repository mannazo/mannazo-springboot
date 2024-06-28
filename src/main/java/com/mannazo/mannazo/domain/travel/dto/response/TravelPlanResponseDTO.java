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
@Setter
public class TravelPlanResponseDTO {
    private UUID tripId;
    private UUID userId;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp createAt;
    private String travelStyle;


    public static TravelPlanResponseDTO fromEntity(TravelPlanEntity travelInfo) {
        return TravelPlanResponseDTO.builder()
                .tripId(travelInfo.getTripId())
                .userId(travelInfo.getUserId())
                .destination(travelInfo.getDestination())
                .startDate(travelInfo.getStartDate())
                .endDate(travelInfo.getEndDate())
                .createAt(travelInfo.getCreateAt())
                .travelStyle(travelInfo.getTravelStyle())
                .build();
    }

    @Builder
    @Getter
    public static class Delete {
        private UUID tripId;
        private String messages;
    }
}
