package com.mannazo.user.service;

import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO getUser(UUID userId);

    List<UserResponseDTO> findAll();

    UserResponseDTO createUser(UserRequestDTO user);

    void deleteUser(UUID userId);

    UserResponseDTO updateUser(UUID userId, UserRequestDTO user);
}
