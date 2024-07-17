package com.mannazo.reviewservice.dto;

import lombok.Getter;

import java.util.UUID;


@Getter
public class ReviewRequestDTO {
    private UUID reviewerId;
    private UUID revieweeId;
    private int rating;
    private String comment;
}
