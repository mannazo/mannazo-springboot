package com.mannazo.shopservice.service;

import com.mannazo.shopservice.domain.ShopRequestDTO;
import com.mannazo.shopservice.domain.ShopResponseDTO;


import java.util.List;
import java.util.UUID;

public interface ShopService {
    ShopResponseDTO getShop(UUID shopId);

    List<ShopResponseDTO> findAll();

    ShopResponseDTO createShop(ShopRequestDTO shop);

    void deleteShop(UUID shopId);

    ShopResponseDTO updateShop(UUID shopId, ShopRequestDTO shop);
}
