package com.mannazo.shopservice.mapper;


import java.util.List;

public interface ShopResponseMapper<E, D> {
    D toShopResponseDTO(E entity);
    List<D> toShopResponseListDTO(List<E> entities);
}
