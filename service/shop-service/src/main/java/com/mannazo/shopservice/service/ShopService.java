package com.mannazo.shopservice.service;

import com.mannazo.shopservice.dto.ProductRequestDTO;
import com.mannazo.shopservice.dto.ProductResponseDTO;


import java.util.List;
import java.util.UUID;

public interface ShopService {
    ProductResponseDTO getProduct(UUID productId);

    List<ProductResponseDTO> findAll();

    ProductResponseDTO createProduct(ProductRequestDTO product);

    void deleteProduct(UUID productId);

    ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO product);
}
