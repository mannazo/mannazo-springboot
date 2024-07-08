package com.mannazo.postservice.dto;

import lombok.Getter;

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
