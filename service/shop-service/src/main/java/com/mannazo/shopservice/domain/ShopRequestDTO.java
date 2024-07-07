package com.mannazo.shopservice.domain;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;
@Getter
public class ShopRequestDTO {
    private String productName;
    private String productImage;
    private String brand;
    private String price;
}
