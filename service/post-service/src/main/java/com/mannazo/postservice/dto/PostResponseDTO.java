package com.mannazo.postservice.dto;

import com.mannazo.postservice.client.dto.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class PostResponseDTO {
    private UUID postId;
    private UUID userId;
    private String travelNationality;
    private String travelCity;
    private LocalDate travelStartDate;
    private LocalDate travelEndDate;
    private String travelStatus;
    private String preferredGender;
    private String travelStyle;
    private String travelPurpose;
    private LocalDateTime createdAt;
    private List<String> imageUrls;
}
