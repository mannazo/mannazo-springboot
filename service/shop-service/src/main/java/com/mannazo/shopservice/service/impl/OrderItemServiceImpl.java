package com.mannazo.shopservice.service.impl;

import com.mannazo.shopservice.dto.OrderItemRequestDTO;
import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.entity.ProductEntity;
import com.mannazo.shopservice.exception.ProductNotFoundException;
import com.mannazo.shopservice.mapStruct.OrderItemRequestMapstruct;
import com.mannazo.shopservice.mapStruct.OrderItemResponseMapStruct;
import com.mannazo.shopservice.repository.OrderItemRepository;
import com.mannazo.shopservice.service.OrderItemService;
import com.mannazo.shopservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemResponseMapStruct orderItemResponseMapStruct;
    private final OrderItemRequestMapstruct orderItemRequestMapstruct;
    private final ShopService shopService;

    @Override
    public OrderItemEntity createOrderItem(OrderEntity orderEntity, OrderItemRequestDTO item) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(orderEntity);

        Optional<ProductEntity> productEntityOptional = shopService.getProductById(item.getProductId());
        ProductEntity productEntity = productEntityOptional
                .orElseThrow(() -> new ProductNotFoundException("상품을 찾을 수 없습니다: " + item.getProductId()));

        orderItemEntity.setProduct(productEntity);
        orderItemEntity.setQuantity(item.getQuantity());

        String price = productEntity.getPrice();
        if (price == null || price.isEmpty()) {
            throw new IllegalStateException("상품의 가격이 설정되지 않았습니다: " + item.getProductId());
        }
        orderItemEntity.setPrice(price);

        log.info("주문이를 저장할 수 있습니다. \n {}", orderItemEntity.toString());
        return orderItemEntity;
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
