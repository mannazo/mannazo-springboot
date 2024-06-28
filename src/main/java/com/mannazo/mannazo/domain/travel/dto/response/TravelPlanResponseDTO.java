package com.mannazo.mannazo.domain.travel.dto.response;

import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.global.util.EnumUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@ToString
@Setter
public class TravelPlanResponseDTO {
    private UUID tripId;
    private UUID userId;
    private String travelCountry;
    private String travelCity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String travelPurpose;
    private EnumUtils.PreferredGender preferredGender;
    private EnumUtils.TravelStatus travelStatus;
    private Timestamp createAt;


    public static TravelPlanResponseDTO fromEntity(TravelPlanEntity travelInfo) {
        return TravelPlanResponseDTO.builder()
                .tripId(travelInfo.getTripId())
                .userId(travelInfo.getUserId())
                .travelCountry(travelInfo.getTravelCountry())
                .travelCity(travelInfo.getTravelCity())
                .startDate(travelInfo.getStartDate())
                .endDate(travelInfo.getEndDate())
                .createAt(travelInfo.getCreateAt())
                .preferredGender(travelInfo.getPreferredGender())
                .travelStatus(travelInfo.getTravelStatus())
                .travelPurpose(travelInfo.getTravelPurpose())
                .build();
    }

    @Builder
    @Getter
    public static class Delete {
        private UUID tripId;
        private String messages;
    }
}
