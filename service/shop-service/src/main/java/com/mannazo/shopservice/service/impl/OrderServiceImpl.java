package com.mannazo.shopservice.service.impl;

import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.dto.OrderRequestDTO;
import com.mannazo.shopservice.dto.OrderResponseDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.mapStruct.OrderItemResponseMapStruct;
import com.mannazo.shopservice.mapStruct.OrderRequestMapStruct;
import com.mannazo.shopservice.mapStruct.OrderResponseMapStruct;
import com.mannazo.shopservice.repository.OrderItemRepository;
import com.mannazo.shopservice.repository.OrderRepository;
import com.mannazo.shopservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderResponseMapStruct orderResponseMapStruct;
    private final OrderRequestMapStruct orderRequestMapStruct;
    private final OrderItemResponseMapStruct orderItemResponseMapStruct;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        OrderEntity orderEntity = orderRequestMapStruct.toEntity(orderRequestDTO);
        orderEntity = orderRepository.save(orderEntity);
        return orderResponseMapStruct.toDTO(orderEntity);
    }

    @Override
    public OrderResponseDTO getOrder(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderResponseMapStruct.toDTO(orderEntity);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderResponseMapStruct::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO updateOrder(UUID orderId, OrderRequestDTO orderRequestDTO) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRequestMapStruct.updateEntityFromDto(orderRequestDTO, orderEntity);
        orderEntity = orderRepository.save(orderEntity);
        return orderResponseMapStruct.toDTO(orderEntity);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderItemResponseDTO> getOrderItems(UUID orderId) {
        try {
            List<OrderItemEntity> orderItems = orderItemRepository.findAllByOrderOrderId(orderId);

            if (orderItems.isEmpty()) {
                return Collections.emptyList();
            }

            return orderItems.stream()
                    .map(orderItemResponseMapStruct::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // 예외 발생 시 로깅하고 빈 리스트 반환 또는 예외 던지기
            log.error("Failed to get order items for orderId: {}", orderId, e);
            throw new RuntimeException("Failed to get order items for orderId: " + orderId, e);
        }
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUser(UUID userId) {
        try {
            List<OrderEntity> orders = orderRepository.findAllByUserId(userId);

            if (orders.isEmpty()) {
                return Collections.emptyList();
            }

            return orders.stream()
                    .map(orderResponseMapStruct::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // 예외 발생 시 로깅하고 빈스트 반환 또는 예외 던지기
            log.error("Failed to get orders for userId: {}", userId, e);
            throw new RuntimeException("Failed to get orders for userId: " + userId, e);
        }
    }
}
