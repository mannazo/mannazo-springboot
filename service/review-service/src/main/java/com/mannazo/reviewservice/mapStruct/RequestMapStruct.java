package com.mannazo.reviewservice.mapStruct;

import com.mannazo.reviewservice.client.UserServiceClient;
import com.mannazo.reviewservice.client.dto.UserResponseDTO;
import com.mannazo.reviewservice.dto.ReviewRequestDTO;
import com.mannazo.reviewservice.entity.ReviewEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RequestMapStruct {

    public abstract ReviewEntity toEntity(ReviewRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDto(ReviewRequestDTO dto, @MappingTarget ReviewEntity entity);

}
