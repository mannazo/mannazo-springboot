package com.mannazo.postservice.mapStruct;

import com.mannazo.postservice.domain.PostResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapper.PostResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostResponseMapStruct extends PostResponseMapper<PostEntity, PostResponseDTO> {
    @Override
    PostResponseDTO toPostResponseDTO(PostEntity postEntity);

    @Override
    List<PostResponseDTO> toPostResponseListDTO(List<PostEntity> postEntities);

}
