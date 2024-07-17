package com.mannazo.shopservice.service.impl;

import com.mannazo.shopservice.dto.OrderItemRequestDTO;
import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.mapStruct.OrderItemRequestMapstruct;
import com.mannazo.shopservice.mapStruct.OrderItemResponseMapStruct;
import com.mannazo.shopservice.repository.OrderItemRepository;
import com.mannazo.shopservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemResponseMapStruct orderItemResponseMapStruct;
    private final OrderItemRequestMapstruct orderItemRequestMapstruct;
    @Override
    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemEntity orderItemEntity = orderItemRequestMapstruct.toEntity(orderItemRequestDTO);
        orderItemEntity = orderItemRepository.save(orderItemEntity);
        return orderItemResponseMapStruct.toDTO(orderItemEntity);
    }

    @Override
    public OrderItemResponseDTO getOrderItem(UUID orderItemId) {
        OrderItemEntity orderItemEntity = orderItemRepository.findById(orderItemId).orElseThrow(() -> new RuntimeException("OrderItem not found"));
        return orderItemResponseMapStruct.toDTO(orderItemEntity);
    }

    @Override
    public List<OrderItemResponseDTO> getAllOrderItems() {
        List<OrderItemEntity> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(orderItemResponseMapStruct::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDTO updateOrderItem(UUID orderItemId, OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemEntity orderItemEntity = orderItemRepository.findById(orderItemId).orElseThrow(() -> new RuntimeException("OrderItem not found"));
        orderItemRequestMapstruct.updateEntityFromDto(orderItemRequestDTO, orderItemEntity);
        orderItemEntity = orderItemRepository.save(orderItemEntity);
        return orderItemResponseMapStruct.toDTO(orderItemEntity);
    }

    @Override
    public void deleteOrderItem(UUID orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
