package com.mannazo.shopservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Entity
@Getter
@Setter
@Table(name = "Orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "addr", nullable = false)
    private String addr;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "merchant_uid", nullable = false)
    private String merchantUid;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(); // 현재 시간으로 기본값 설정
        }
    }
}
