package com.mannazo.communityservice.mapStruct;

import com.mannazo.communityservice.dto.CommunityRequestDTO;
import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.mapper.CommunityRequestMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommunityRequestMapStruct extends CommunityRequestMapper<CommunityEntity, CommunityRequestDTO> {

    CommunityEntity toEntity(CommunityRequestDTO dto);

    List<CommunityEntity> toEntityList(List<CommunityRequestDTO> dtos);

    //기존 CommunityEntity를 수정하고, Null값을 처리하기 위해 따로 작성
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommunityFromDto(CommunityRequestDTO dto, @MappingTarget CommunityEntity entity);
}
