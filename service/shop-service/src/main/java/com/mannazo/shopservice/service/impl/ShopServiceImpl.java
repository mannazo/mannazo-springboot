package com.mannazo.shopservice.service.impl;

import com.mannazo.shopservice.entity.ImageEntity;
import com.mannazo.shopservice.entity.ProductEntity;
import com.mannazo.shopservice.mapStruct.ProductRequestMapStruct;
import com.mannazo.shopservice.repository.ProductRepository;
import com.mannazo.shopservice.dto.ProductRequestDTO;
import com.mannazo.shopservice.dto.ProductResponseDTO;
import com.mannazo.shopservice.mapStruct.ProductResponseMapStruct;
import com.mannazo.shopservice.service.ShopService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ProductRepository productRepository;
    private final ProductResponseMapStruct productResponseMapStruct;
    private final ProductRequestMapStruct productRequestMapStruct;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO product) {
        ProductEntity productEntity = productRequestMapStruct.toEntity(product);

        // 이미지 URL을 ImageEntity로 변환하여 설정
        if (product.getImageUrls() != null && !product.getImageUrls().isEmpty()) {
            List<ImageEntity> images = product.getImageUrls().stream()
                    .map(url -> {
                        ImageEntity imageEntity = new ImageEntity();
                        imageEntity.setFilePath(url);
                        imageEntity.setProduct(productEntity); // 이미지와 게시물 간의 양방향 매핑 설정
                        return imageEntity;
                    })
                    .collect(Collectors.toList());
            productEntity.setImages(images); // 게시물 엔티티에 이미지 엔티티 리스트 설정
        }
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productResponseMapStruct.toDTO(savedEntity);
    }

//    @Override
//    public ProductResponseDTO getProduct(UUID productId) {
//        Optional<ProductEntity> productEntity = productRepository.findById(productId);
//        ProductResponseDTO productInfo = productResponseMapStruct.toDTO(productEntity.orElse(null));
//        return productInfo;
//    }

    @Override
    public Optional<ProductEntity> getProduct(UUID productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<ProductResponseDTO> findAll() {

        List<ProductEntity> list = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = productResponseMapStruct.toDTOList(list);

        return productResponseDTOS;
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO product) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Shop not found with id: " + productId));
        productRequestMapStruct.updateEntityFromDto(product, productEntity);
        productRepository.save(productEntity);
        return productResponseMapStruct.toDTO(productEntity);
    }

    @Override
    public Optional<ProductEntity> getProductById(UUID productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isPresent()) {
            return productEntity;
        }
        return productEntity;
    }
}
