package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.ShopResponseDTO;
import com.mannazo.shopservice.entity.ShopEntity;
import com.mannazo.shopservice.mapper.ShopResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopResponseMapStruct extends ShopResponseMapper<ShopEntity, ShopResponseDTO> {
    @Override
    ShopResponseDTO toShopResponseDTO(ShopEntity shopEntity);

    @Override
    List<ShopResponseDTO> toShopResponseListDTO(List<ShopEntity> shopEntities);

}
