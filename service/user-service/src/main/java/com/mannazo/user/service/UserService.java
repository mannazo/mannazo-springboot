package com.mannazo.user.service;

import com.mannazo.user.client.auth.LoginRequestDTO;
import com.mannazo.user.client.auth.LoginResponseDTO;
import com.mannazo.user.dto.UserCreationRequestDTO;
import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDTO getUser(UUID userId);

    List<UserResponseDTO> findAll();

    LoginResponseDTO createUser(UserCreationRequestDTO newUser);

    void deleteUser(UUID userId);

    UserResponseDTO updateUser(UUID userId, UserRequestDTO user);
}
