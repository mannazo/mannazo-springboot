package com.mannazo.postservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PostResponseDTO {
    private UUID tripId;
    private String userId;
    private String tNationality;
    private String tCity;
    private String startDate;
    private String endDate;
    private String status;
    private String wGender;
    private String tStyle;
    private String tGoal;
}
