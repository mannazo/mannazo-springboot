package com.mannazo.shopservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "OrderItem")
public class OrderItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id", nullable = false, unique = true)
    private UUID orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private String price;
}
