package com.mannazo.postservice.mapStruct.post;

import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapper.RequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostRequestMapStruct extends RequestMapper<PostEntity, PostRequestDTO> {

    PostEntity toEntity(PostRequestDTO dto);

    List<PostEntity> toEntityList(List<PostRequestDTO> dtos);

    //기존 PostEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromDto(PostRequestDTO dto, @MappingTarget PostEntity entity);
}
