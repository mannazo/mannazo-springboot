package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.ProductResponseDTO;
import com.mannazo.shopservice.entity.ImageEntity;
import com.mannazo.shopservice.entity.ProductEntity;
import com.mannazo.shopservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductResponseMapStruct extends ResponseMapper<ProductEntity, ProductResponseDTO> {
    @Override
    ProductResponseDTO toDTO(ProductEntity shopEntity);

    @Override
    List<ProductResponseDTO> toDTOList(List<ProductEntity> shopEntities);

    // Custom mapping method for imageUrls
    default List<String> mapImages(List<ImageEntity> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(ImageEntity::getFilePath)
                .collect(Collectors.toList());
    }
}
