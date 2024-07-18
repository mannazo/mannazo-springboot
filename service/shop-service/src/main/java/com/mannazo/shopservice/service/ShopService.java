package com.mannazo.shopservice.service;

import com.mannazo.shopservice.dto.ProductRequestDTO;
import com.mannazo.shopservice.dto.ProductResponseDTO;
import com.mannazo.shopservice.entity.ProductEntity;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShopService {
    Optional<ProductEntity> getProduct(UUID productId);

    List<ProductResponseDTO> findAll();

    ProductResponseDTO createProduct(ProductRequestDTO product);

    void deleteProduct(UUID productId);

    ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO product);

    Optional<ProductEntity> getProductById(UUID productId);
}
