package com.mannazo.shopservice.controller;

import com.mannazo.shopservice.dto.ProductRequestDTO;
import com.mannazo.shopservice.dto.ProductResponseDTO;
import com.mannazo.shopservice.service.ShopService;
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

    @GetMapping("/")
    public String User() {
        return "Hello Shop-Service";
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> createdProduct(@RequestBody ProductRequestDTO product) {
        ProductResponseDTO createdProduct = shopService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID productId) {
        ProductResponseDTO product = shopService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> products = shopService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequestDTO product) {
        ProductResponseDTO updateShop = shopService.updateProduct(productId, product);
        return ResponseEntity.status(HttpStatus.OK).body(updateShop);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID productId) {
        shopService.deleteProduct(productId);
        String text = productId+"가 성공적으로 삭제되었습니다.";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(text);
    }
}
