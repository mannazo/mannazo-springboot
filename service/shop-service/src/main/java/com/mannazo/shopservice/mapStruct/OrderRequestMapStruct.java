package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.OrderRequestDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.mapper.RequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapStruct extends RequestMapper<OrderEntity, OrderRequestDTO> {
    @Override
    OrderEntity toEntity(OrderRequestDTO dto);
    @Override
    List<OrderEntity> toEntityList(List<OrderRequestDTO> dtos);

    //기존 ShopEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(OrderRequestDTO dto, @MappingTarget OrderEntity entity);
}
