package com.mannazo.user.mapStruct;

import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.mapper.UserRequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapStruct extends UserRequestMapper<UserEntity, UserRequestDTO> {

    UserEntity toEntity(UserRequestDTO dto);

    List<UserEntity> toEntityList(List<UserRequestDTO> dtos);

    //기존 UserEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserRequestDTO dto, @MappingTarget UserEntity entity);
}
