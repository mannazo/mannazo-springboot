package com.mannazo.communityservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class CommunityRequestDTO {
    private UUID userId;
    private String travelNationality;
    private String travelCity;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private String travelStatus;
    private String preferredGender;
    private String travelStyle;
    private String travelPurpose;
    private List<String> imageUrls;
}
