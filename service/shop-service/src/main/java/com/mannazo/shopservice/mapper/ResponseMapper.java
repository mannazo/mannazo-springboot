package com.mannazo.shopservice.mapper;


import java.util.List;

public interface ResponseMapper<E, D> {
    D toDTO(E entity);
    List<D> toDTOList(List<E> entities);
}
