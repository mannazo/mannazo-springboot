package com.mannazo.mannazo.domain.travel.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TravelPlanRequestDTO {
    private UUID tripId;
    private String userId;
    private String destinationId;
    private Date startDate;
    private Date endDate;
    private String interests;
}
