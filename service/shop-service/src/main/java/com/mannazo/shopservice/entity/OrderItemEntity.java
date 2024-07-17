package com.mannazo.shopservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "OrderItem")
public class OrderItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id", nullable = false, unique = true)
    private UUID orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "total_price", nullable = false)
    private String totalPrice;
}
