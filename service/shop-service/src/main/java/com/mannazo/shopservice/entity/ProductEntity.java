package com.mannazo.shopservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId;

    @Column(name = "product_name")
    private String productName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private String price;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;

    public String getPrice() {
        return price != null ? price : "0";
    }

    @PrePersist
    protected void onCreate() {
        if (this.createAt == null) {
            this.createAt = LocalDateTime.now(); // 현재 시간으로 기본값 설정
        }
    }
}
