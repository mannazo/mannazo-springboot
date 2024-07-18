package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderItemRequestDTO {
    private UUID productId;
    private int quantity;
}
