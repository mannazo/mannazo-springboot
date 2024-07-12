package com.mannazo.communityservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.sql.Timestamp;

@Getter
@Setter
public class CommentResponseDTO {
    private UUID commentId;
    private UUID communityId;
    private UUID userId;
    private String content;
    private Timestamp createAt;
}