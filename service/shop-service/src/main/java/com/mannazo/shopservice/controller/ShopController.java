package com.mannazo.shopservice.controller;

import com.mannazo.shopservice.domain.ShopRequestDTO;
import com.mannazo.shopservice.domain.ShopResponseDTO;
import com.mannazo.shopservice.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("")
    public ResponseEntity<ShopResponseDTO> createShop(@RequestBody ShopRequestDTO shop) {
        ShopResponseDTO createdShop = shopService.createShop(shop);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShop);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<ShopResponseDTO> getShop(@PathVariable UUID shopId) {
        ShopResponseDTO shop = shopService.getShop(shopId);
        return ResponseEntity.status(HttpStatus.OK).body(shop);
    }

    @GetMapping("")
    public ResponseEntity<List<ShopResponseDTO>> findAll() {
        List<ShopResponseDTO> shops = shopService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(shops);
    }

    @PutMapping("/{shopId}")
    public ResponseEntity<ShopResponseDTO> updateShop(@PathVariable UUID shopId, @RequestBody ShopRequestDTO shop) {
        ShopResponseDTO updateShop = shopService.updateShop(shopId, shop);
        return ResponseEntity.status(HttpStatus.OK).body(updateShop);
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<String> deleteShop(@PathVariable UUID shopId) {
        shopService.deleteShop(shopId);
        String text = shopId+"가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }
}
