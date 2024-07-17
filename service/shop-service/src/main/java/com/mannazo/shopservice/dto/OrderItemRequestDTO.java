package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemRequestDTO {
    private UUID productId;
    private int quantity;
    private String price;
}
