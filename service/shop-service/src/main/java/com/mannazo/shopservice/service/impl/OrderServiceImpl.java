package com.mannazo.shopservice.service.impl;

import com.mannazo.shopservice.dto.OrderItemRequestDTO;
import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.dto.OrderRequestDTO;
import com.mannazo.shopservice.dto.OrderResponseDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.entity.OrderStatus;
import com.mannazo.shopservice.entity.ProductEntity;
import com.mannazo.shopservice.exception.OrderCreationException;
import com.mannazo.shopservice.mapStruct.OrderItemResponseMapStruct;
import com.mannazo.shopservice.mapStruct.OrderRequestMapStruct;
import com.mannazo.shopservice.mapStruct.OrderResponseMapStruct;
import com.mannazo.shopservice.repository.OrderItemRepository;
import com.mannazo.shopservice.repository.OrderRepository;
import com.mannazo.shopservice.service.OrderItemService;
import com.mannazo.shopservice.service.OrderService;
import com.mannazo.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private final ShopService shopService;
    private final OrderItemService orderItemService;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        OrderEntity orderEntity = orderRequestMapStruct.toEntity(orderRequestDTO);
        orderEntity.setTotalPrice("0"); // 초기값 설정

        // OrderItems 리스트 초기화
        List<OrderItemEntity> orderItems = new ArrayList<>();

        for (OrderItemRequestDTO item : orderRequestDTO.getOrderItems()) {
            OrderItemEntity orderItemEntity = orderItemService.createOrderItem(orderEntity, item);
            orderItems.add(orderItemEntity);
            log.debug("Created order item: {}", orderItemEntity);
        }

        // OrderEntity에 orderItems 설정
        orderEntity.setOrderItems(orderItems);

        // 총 가격 계산
        orderEntity.calculateTotalPrice();
        log.debug("Calculated total price: {}", orderEntity.getTotalPrice());

        orderEntity.setOrderStatus(OrderStatus.Pending);

        try {
            // 트랜잭션이 올바르게 시작되었는지 확인
            orderRepository.save(orderEntity); // 이 시점에 모든 연관된 엔티티가 저장됩니다.
            log.debug("Saved order: {}", orderEntity);
        } catch (Exception e) {
            log.error("Error saving order", e);
            throw new OrderCreationException("주문 생성 중 오류가 발생했습니다.", e);
        }

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
