package com.mannazo.user.mapper;


import java.util.List;

public interface UserResponseMapper<E, D> {
    D toUserResponseDTO(E entity);
    List<D> toUserResponseListDTO(List<E> entities);
}
