package com.mannazo.user.mapStruct;

import com.mannazo.user.dto.UserResponseDTO;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.mapper.UserResponseMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapStruct extends UserResponseMapper<UserEntity, UserResponseDTO> {
    @Override
    UserResponseDTO toUserResponseDTO(UserEntity userEntity);

    @Override
    List<UserResponseDTO> toUserResponseListDTO(List<UserEntity> userEntities);

    @Override
    @IterableMapping(qualifiedByName = "toUserResponseMap")
    default Map<UUID, UserResponseDTO> toUserResponseMap(List<UserResponseDTO> userResponseDTOS) {
        return userResponseDTOS.stream()
                .collect(Collectors.toMap(UserResponseDTO::getUserId, dto -> dto));
    }
}
