package com.mannazo.postservice.mapStruct.commnet;

import com.mannazo.postservice.dto.CommentResponseDTO;
import com.mannazo.postservice.entity.CommentEntity;
import com.mannazo.postservice.mapper.ResponseMapper;
import com.mannazo.postservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentResponseMapStruct extends ResponseMapper<CommentEntity, CommentResponseDTO> {
    @Override
    CommentResponseDTO toResponseDTO(CommentEntity commentEntity);

    @Override
    List<CommentResponseDTO> toResponseListDTO(List<CommentEntity> commentEntities);

}
