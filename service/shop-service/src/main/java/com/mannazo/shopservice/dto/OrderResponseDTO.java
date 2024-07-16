package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseDTO {
    private UUID orderId;
    private UUID userId;
    private String name;
    private String tel;
    private String email;
    private String addr;
    private String postcode;
    private String merchantUid;
    private List<OrderItemResponseDTO> orderItems;
    private String orderStatus;
}
