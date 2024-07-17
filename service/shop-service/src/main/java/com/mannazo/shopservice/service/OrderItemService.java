package com.mannazo.shopservice.service;

import com.mannazo.shopservice.dto.OrderItemRequestDTO;
import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.entity.OrderItemEntity;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    OrderItemEntity createOrderItem(OrderEntity orderEntity, OrderItemRequestDTO orderItemRequestDTO);
    OrderItemResponseDTO getOrderItem(UUID orderItemId);
    List<OrderItemResponseDTO> getAllOrderItems();
    OrderItemResponseDTO updateOrderItem(UUID orderItemId, OrderItemRequestDTO orderItemRequestDTO);
    void deleteOrderItem(UUID orderItemId);
}
