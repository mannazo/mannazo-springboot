package com.mannazo.postservice.domain;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;
@Getter
public class PostRequestDTO {
    private UUID userId;
    private String travelNationality;
    private String travelCity;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private String travelStatus;
    private String preferredGender;
    private String travelStyle;
    private String travelPurpose;
}
