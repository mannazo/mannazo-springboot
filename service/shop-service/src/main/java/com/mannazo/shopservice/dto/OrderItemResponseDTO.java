package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemResponseDTO {
    private UUID orderItemId;
    private UUID productId;
    private int quantity;
    private String price;
    private String totalPrice;
}
