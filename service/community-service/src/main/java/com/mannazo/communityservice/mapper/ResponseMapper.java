package com.mannazo.communityservice.mapper;


import java.util.List;

public interface ResponseMapper<E, D> {
    D toResponseDTO(E entity);
    List<D> toResponseListDTO(List<E> entities);
}
