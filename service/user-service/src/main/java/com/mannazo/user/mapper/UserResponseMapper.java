package com.mannazo.user.mapper;


import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserResponseMapper<E, D> {
    D toUserResponseDTO(E entity);
    List<D> toUserResponseListDTO(List<E> entities);
    Map<UUID, D> toUserResponseMap(List<D> DTOs);
}
