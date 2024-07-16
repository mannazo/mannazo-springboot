package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequestDTO {
    private UUID userId;
    private String name;
    private String tel;
    private String email;
    private String addr;
    private String postcode;
    private String merchantUid;
    private List<OrderItemRequestDTO> orderItems;
    private String orderStatus;
}
