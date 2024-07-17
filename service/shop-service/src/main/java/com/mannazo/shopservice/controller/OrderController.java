package com.mannazo.shopservice.controller;

import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.dto.OrderRequestDTO;
import com.mannazo.shopservice.dto.OrderResponseDTO;
import com.mannazo.shopservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO createdOrder = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable UUID orderId) {
        OrderResponseDTO order = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable UUID orderId, @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO updatedOrder = orderService.updateOrder(orderId, orderRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemResponseDTO>> getOrderItems(@PathVariable UUID orderId) {
        List<OrderItemResponseDTO> orderItems = orderService.getOrderItems(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderItems);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUser(@PathVariable UUID userId) {
        List<OrderResponseDTO> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

}
