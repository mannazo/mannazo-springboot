package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemResponseDTO {
    private UUID orderItemId;
    private UUID productId;
    private int quantity;
    private String price;
}
