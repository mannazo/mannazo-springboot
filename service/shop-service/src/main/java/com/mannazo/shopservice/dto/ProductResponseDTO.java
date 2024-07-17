package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class ProductResponseDTO {
    private UUID productId;
    private String productName;
    private List<String> images;
    private String description;
    private String category;
    private int stock;
    private String price;
    private Timestamp createAt;
    private List<OrderItemResponseDTO> orderItems;
}