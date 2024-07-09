package com.mannazo.postservice.mapStruct.post;

import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostResponseMapStruct extends ResponseMapper<PostEntity, PostResponseDTO> {
    @Override
    PostResponseDTO toResponseDTO(PostEntity postEntity);

    @Override
    List<PostResponseDTO> toResponseListDTO(List<PostEntity> postEntities);

}
