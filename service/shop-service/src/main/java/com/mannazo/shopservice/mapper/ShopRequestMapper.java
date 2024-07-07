package com.mannazo.shopservice.mapper;

import java.util.List;

public interface ShopRequestMapper<E, D> {
    E toEntity(D dto);
    List<E> toEntityList(List<D> dtos);
}
