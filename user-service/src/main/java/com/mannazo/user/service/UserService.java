package com.mannazo.user.service;

import com.mannazo.user.domain.UserRequestDTO;
import com.mannazo.user.domain.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO getUser(UUID userId);

    List<UserResponseDTO> findAll();

    void addUser(UserRequestDTO user);

    void deleteUser(UUID userId);

    void updateUser(UUID userId, UserRequestDTO user);
}
