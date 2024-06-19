package com.mannazo.mannazo.domain.travel.dto.request;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Timestamp createAt;
    private String interests;

    public TravelPlanEntity toEntity() {
        return TravelPlanEntity.builder()
                .tripId(tripId)
                .userId(userId)
                .destination(destination)
                .startDate(startDate)
                .endDate(endDate)
                .createAt(createAt)
                .interests(interests)
                .build();
    }

    @Getter
    @Setter
    public static class TripIdOnly {
        private String tripId;
    }
}
