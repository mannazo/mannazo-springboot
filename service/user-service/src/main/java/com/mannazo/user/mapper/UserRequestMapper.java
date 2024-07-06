package com.mannazo.user.mapper;

import java.util.List;

public interface UserRequestMapper<E, D> {
    E toEntity(D dto);
    List<E> toEntityList(List<D> dtos);
}
