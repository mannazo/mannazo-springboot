package com.mannazo.reviewservice.mapStruct;

import com.mannazo.reviewservice.client.UserServiceClient;
import com.mannazo.reviewservice.dto.ReviewResponseDTO;
import com.mannazo.reviewservice.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ResponseMapStruct {
    public abstract ReviewResponseDTO toReviewResponseDTO(ReviewEntity reviewEntity);
    public abstract List<ReviewResponseDTO> toReviewResponseListDTO(List<ReviewEntity> reviewEntities);
}
