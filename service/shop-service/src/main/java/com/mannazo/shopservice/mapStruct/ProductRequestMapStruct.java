package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.ProductRequestDTO;
import com.mannazo.shopservice.entity.ProductEntity;
import com.mannazo.shopservice.mapper.RequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRequestMapStruct extends RequestMapper<ProductEntity, ProductRequestDTO> {

    ProductEntity toEntity(ProductRequestDTO dto);

    List<ProductEntity> toEntityList(List<ProductRequestDTO> dtos);

    //기존 ShopEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductRequestDTO dto, @MappingTarget ProductEntity entity);
}
