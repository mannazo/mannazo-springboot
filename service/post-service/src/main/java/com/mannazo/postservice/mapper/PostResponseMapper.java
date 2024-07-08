package com.mannazo.postservice.mapper;


import java.util.List;

public interface PostResponseMapper<E, D> {
    D toPostResponseDTO(E entity);
    List<D> toPostResponseListDTO(List<E> entities);
}
