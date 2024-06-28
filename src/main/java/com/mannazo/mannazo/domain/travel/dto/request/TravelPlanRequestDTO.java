package com.mannazo.mannazo.domain.travel.dto.request;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.global.util.EnumUtils;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TravelPlanRequestDTO {
    private UUID tripId;
    private UUID userId;
    private String travelCountry;
    private String travelCity;
    private LocalDate startDate;
    private LocalDate endDate;
    private EnumUtils.PreferredGender preferredGender;
    private EnumUtils.TravelStatus travelStatus;
    private String travelPurpose;


    public TravelPlanEntity toEntity() {
        return TravelPlanEntity.builder()
                .tripId(tripId)
                .userId(userId)
                .travelCountry(travelCountry)
                .travelCity(travelCity)
                .startDate(startDate)
                .endDate(endDate)
                .preferredGender(preferredGender)
                .travelStatus(travelStatus)
                .travelPurpose(travelPurpose)
                .build();
    }

    @Getter
    @Setter
    public static class TripIdOnly {
        private String tripId;
    }
}
