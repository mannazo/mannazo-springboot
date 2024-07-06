package com.mannazo.user.mapStruct;

import com.mannazo.user.domain.UserResponseDTO;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.mapper.UserResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapStruct extends UserResponseMapper<UserEntity, UserResponseDTO> {
    @Override
    UserResponseDTO toUserResponseDTO(UserEntity userEntity);

    @Override
    List<UserResponseDTO> toUserResponseListDTO(List<UserEntity> userEntities);

}
