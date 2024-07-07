package com.mannazo.shopservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class ShopResponseDTO {
    private UUID shopId;
    private String productName;
    private String productImage;
    private String brand;
    private String price;
    private Timestamp createAt;
}