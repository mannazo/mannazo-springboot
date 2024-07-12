package com.mannazo.communityservice.mapStruct;

import com.mannazo.communityservice.client.dto.UserResponseDTO;
import com.mannazo.communityservice.dto.response.CommunityResponseDTO;
import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommunityResponseMapStruct extends ResponseMapper<CommunityEntity, CommunityResponseDTO> {
    @Override
    CommunityResponseDTO toResponseDTO(CommunityEntity communityEntity);

    @Override
    List<CommunityResponseDTO> toResponseListDTO(List<CommunityEntity> communityEntities);

}
