package com.mannazo.shopservice.controller;

import com.mannazo.shopservice.dto.OrderItemRequestDTO;
import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemResponseDTO> createOrderItem(@RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemResponseDTO createdOrderItem = orderItemService.createOrderItem(orderItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemResponseDTO> getOrderItem(@PathVariable UUID orderItemId) {
        OrderItemResponseDTO orderItem = orderItemService.getOrderItem(orderItemId);
        return ResponseEntity.status(HttpStatus.OK).body(orderItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDTO>> getAllOrderItems() {
        List<OrderItemResponseDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.status(HttpStatus.OK).body(orderItems);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemResponseDTO> updateOrderItem(@PathVariable UUID orderItemId, @RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        OrderItemResponseDTO updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrderItem);
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable UUID orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
