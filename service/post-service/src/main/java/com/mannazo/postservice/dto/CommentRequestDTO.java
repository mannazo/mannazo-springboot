package com.mannazo.postservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class CommentRequestDTO {
    private UUID postId;
    private UUID userId;
    private String comment;
    private Timestamp createAt;

}
