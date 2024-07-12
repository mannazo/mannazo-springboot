package com.mannazo.communityservice.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class CommunityRequestDTO {
    private UUID userId;
    private String title;
    private String description;
    private List<String> imageUrls;
}
