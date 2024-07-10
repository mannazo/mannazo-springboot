package com.mannazo.postservice.mapStruct.post;

import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.ImageEntity;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.mapper.ResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostResponseMapStruct extends ResponseMapper<PostEntity, PostResponseDTO> {
    @Override
    @Mapping(source = "images", target = "imageUrls")
    PostResponseDTO toResponseDTO(PostEntity postEntity);

    @Override
    List<PostResponseDTO> toResponseListDTO(List<PostEntity> postEntities);

    // Custom mapping method for imageUrls
    default List<String> mapImages(List<ImageEntity> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(ImageEntity::getFilePath)
                .collect(Collectors.toList());
    }
}
