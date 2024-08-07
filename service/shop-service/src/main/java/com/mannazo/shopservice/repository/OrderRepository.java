package com.mannazo.shopservice.repository;

import com.mannazo.shopservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findAllByUserId(UUID userId);

    List<OrderEntity> findTop10ByOrderByCreatedAtDesc();
}
