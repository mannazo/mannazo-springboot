package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.domain.ShopRequestDTO;
import com.mannazo.shopservice.entity.ShopEntity;
import com.mannazo.shopservice.mapper.ShopRequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopRequestMapStruct extends ShopRequestMapper<ShopEntity, ShopRequestDTO> {

    ShopEntity toEntity(ShopRequestDTO dto);

    List<ShopEntity> toEntityList(List<ShopRequestDTO> dtos);

    //기존 ShopEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShopFromDto(ShopRequestDTO dto, @MappingTarget ShopEntity entity);
}
