package com.mannazo.shopservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderRequestDTO {
    private UUID userId;
    private String name;
    private String tel;
    private String email;
    private String addr;
    private String postcode;
    private String merchantUid;
    private String totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemRequestDTO> orderItems;
    private String orderStatus;
}
