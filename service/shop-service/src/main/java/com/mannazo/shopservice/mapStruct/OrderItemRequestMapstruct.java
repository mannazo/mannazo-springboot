package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.OrderItemRequestDTO;
import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.dto.OrderRequestDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.mapper.RequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemRequestMapstruct extends RequestMapper<OrderItemEntity, OrderItemRequestDTO> {
    @Override
    OrderItemEntity toEntity(OrderItemRequestDTO dto);
    @Override
    List<OrderItemEntity> toEntityList(List<OrderItemRequestDTO> dtos);

    //기존 ShopEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(OrderItemRequestDTO dto, @MappingTarget OrderItemEntity entity);
}
