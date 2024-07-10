package com.mannazo.communityservice.mapStruct;

import com.mannazo.communityservice.dto.CommunityResponseDTO;
import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.mapper.CommunityResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommunityResponseMapStruct extends CommunityResponseMapper<CommunityEntity, CommunityResponseDTO> {
    @Override
    CommunityResponseDTO toCommunityResponseDTO(CommunityEntity communityEntity);

    @Override
    List<CommunityResponseDTO> toCommunityResponseListDTO(List<CommunityEntity> communityEntities);

}
