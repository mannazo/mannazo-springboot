package com.mannazo.postservice.mapper;

import java.util.List;

public interface PostRequestMapper<E, D> {
    E toEntity(D dto);
    List<E> toEntityList(List<D> dtos);
}
