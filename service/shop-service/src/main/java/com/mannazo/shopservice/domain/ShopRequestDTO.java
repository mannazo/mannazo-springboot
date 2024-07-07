package com.mannazo.shopservice.domain;

import lombok.Getter;


@Getter
public class ShopRequestDTO {
    private String productName;
    private String productImage;
    private String brand;
    private String price;
}
