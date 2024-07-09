package com.mannazo.postservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CommentResponseDTO {
    private UUID commentId;
    private UUID postId;
    private UUID userId;
    private String comment;
    private Timestamp createAt;
}
