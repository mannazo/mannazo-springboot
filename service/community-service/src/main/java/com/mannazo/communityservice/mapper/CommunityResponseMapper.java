package com.mannazo.communityservice.mapper;


import java.util.List;

public interface CommunityResponseMapper<E, D> {
    D toCommunityResponseDTO(E entity);
    List<D> toCommunityResponseListDTO(List<E> entities);
}
