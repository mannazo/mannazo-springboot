package com.mannazo.shopservice.mapStruct;

import com.mannazo.shopservice.dto.OrderResponseDTO;
import com.mannazo.shopservice.entity.OrderEntity;
import com.mannazo.shopservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderResponseMapStruct extends ResponseMapper<OrderEntity, OrderResponseDTO> {

    @Override
    OrderResponseDTO toDTO(OrderEntity entity);

    @Override
    List<OrderResponseDTO> toDTOList(List<OrderEntity> entities);
}
