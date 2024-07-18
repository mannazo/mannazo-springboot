package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.OrderItemResponseDTO;
import com.mannazo.shopservice.dto.OrderResponseDTO;
import com.mannazo.shopservice.entity.OrderItemEntity;
import com.mannazo.shopservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemResponseMapStruct extends ResponseMapper<OrderItemEntity, OrderItemResponseDTO> {
    @Override
    @Mapping(source = "product.productId", target = "productId")
    OrderItemResponseDTO toDTO(OrderItemEntity entity);

    @Override
    List<OrderItemResponseDTO> toDTOList(List<OrderItemEntity> entities);
}
