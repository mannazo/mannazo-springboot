package com.mannazo.communityservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class CommunityResponseDTO {
    private UUID communityId;
    private UUID userId;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private String status;
    private LocalDateTime lastUpdated;
    private Long viewCount;
    private List<ImageResponseDTO> images;
    private List<CommentResponseDTO> comments;
    private int likesCount;  // 좋아요 수
}
