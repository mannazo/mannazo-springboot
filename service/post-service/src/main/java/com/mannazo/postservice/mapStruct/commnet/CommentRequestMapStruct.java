package com.mannazo.postservice.mapStruct.commnet;

import com.mannazo.postservice.dto.CommentRequestDTO;
import com.mannazo.postservice.entity.CommentEntity;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapper.RequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentRequestMapStruct extends RequestMapper<CommentEntity, CommentRequestDTO> {
    CommentEntity toEntity(CommentRequestDTO dto);

    List<CommentEntity> toEntityList(List<CommentRequestDTO> dtos);

    //기존 CommentEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommentFromDto(CommentRequestDTO dto, @MappingTarget CommentEntity entity);
}
