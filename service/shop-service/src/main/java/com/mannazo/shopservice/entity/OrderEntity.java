package com.mannazo.shopservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // totalPrice를 계산하는 메서드
    public void calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemEntity item : orderItems) {
            String price = item.getPrice();
            if (price != null && !price.isEmpty()) {
                total = total.add(new BigDecimal(price).multiply(BigDecimal.valueOf(item.getQuantity())));
            } else {
                log.error("주문 항목의 가격이 설정되지 않았습니다: {}", item.getOrderItemId());
                throw new IllegalStateException("주문 항목의 가격이 설정되지 않았습니다: " + item.getOrderItemId());
            }
        }
        this.totalPrice = total.toString();
        log.debug("Total price calculated: {}", this.totalPrice);
    }
}
