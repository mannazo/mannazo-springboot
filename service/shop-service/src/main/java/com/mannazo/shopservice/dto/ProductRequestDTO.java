package com.mannazo.shopservice.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;


@Getter
public class ProductRequestDTO {
    private String productName;
    private List<String> imageUrls;
    private String description;
    private String category;
    private int stock;
    private String price;
    private Timestamp createAt;
    private List<OrderItemRequestDTO> orderItems;
}
