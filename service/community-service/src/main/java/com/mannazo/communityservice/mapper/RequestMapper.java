package com.mannazo.communityservice.mapper;

import java.util.List;

public interface RequestMapper<E, D> {
    E toEntity(D dto);
    List<E> toEntityList(List<D> dtos);
}
