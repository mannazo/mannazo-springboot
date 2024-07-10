package com.mannazo.communityservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentRequestDTO {
    private UUID communityId;
    private UUID userId;
    private String content;
}