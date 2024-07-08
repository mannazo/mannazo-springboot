package com.mannazo.shopservice.service.impl;

import com.mannazo.shopservice.repository.ShopRepository;
import com.mannazo.shopservice.domain.ShopRequestDTO;
import com.mannazo.shopservice.domain.ShopResponseDTO;
import com.mannazo.shopservice.entity.ShopEntity;
import com.mannazo.shopservice.mapStruct.ShopRequestMapStruct;
import com.mannazo.shopservice.mapStruct.ShopResponseMapStruct;
import com.mannazo.shopservice.repository.ShopRepository;
import com.mannazo.shopservice.service.ShopService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopResponseMapStruct shopResponseMapStruct;
    private final ShopRequestMapStruct shopRequsetMapStruct;

    @Override
    public ShopResponseDTO createShop(ShopRequestDTO shop) {
        ShopEntity shopEntity = shopRequsetMapStruct.toEntity(shop);
        ShopEntity savedEntity = shopRepository.save(shopEntity);
        return shopResponseMapStruct.toShopResponseDTO(savedEntity);
    }

    @Override
    public ShopResponseDTO getShop(UUID shopId) {
        Optional<ShopEntity> shopEntity = shopRepository.findById(shopId);
        ShopResponseDTO shopInfo = shopResponseMapStruct.toShopResponseDTO(shopEntity.orElse(null));
        return shopInfo;
    }

    @Override
    public List<ShopResponseDTO> findAll() {

        List<ShopEntity> list = shopRepository.findAll();
        List<ShopResponseDTO> shopResponseDTOS = shopResponseMapStruct.toShopResponseListDTO(list);

        return shopResponseDTOS;
    }

    @Override
    public void deleteShop(UUID shopId) {
        shopRepository.deleteById(shopId);
    }

    @Override
    public ShopResponseDTO updateShop(UUID shopId, ShopRequestDTO shop) {
        ShopEntity shopEntity = shopRepository.findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found with id: " + shopId));
        shopRequsetMapStruct.updateShopFromDto(shop, shopEntity);
        shopRepository.save(shopEntity);
        return shopResponseMapStruct.toShopResponseDTO(shopEntity);
    }
}
