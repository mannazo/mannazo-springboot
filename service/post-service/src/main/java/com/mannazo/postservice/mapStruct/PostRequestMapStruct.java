package com.mannazo.postservice.mapStruct;

import com.mannazo.postservice.domain.PostRequestDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapper.PostRequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostRequestMapStruct extends PostRequestMapper<PostEntity, PostRequestDTO> {

    PostEntity toEntity(PostRequestDTO dto);

    List<PostEntity> toEntityList(List<PostRequestDTO> dtos);

    //기존 PostEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromDto(PostRequestDTO dto, @MappingTarget PostEntity entity);
}
