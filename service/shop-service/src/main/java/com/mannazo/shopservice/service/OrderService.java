package com.mannazo.shopservice.service;

import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.dto.OrderRequestDTO;
import com.mannazo.shopservice.dto.OrderResponseDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO getOrder(UUID orderId);
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO updateOrder(UUID orderId, OrderRequestDTO orderRequestDTO);
    void deleteOrder(UUID orderId);

    List<OrderItemResponseDTO> getOrderItems(UUID orderId);

    List<OrderResponseDTO> getOrdersByUser(UUID userId);
}
