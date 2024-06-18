package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        UserEntity user = userRepository.save(userRequestDTO.toEntity());
        return UserResponseDTO.fromEntity(user);
    }

    @Override
    public UserResponseDTO modifyUserDetails(UserRequestDTO userRequestDTO) {
        UserEntity user = userRepository.save(userRequestDTO.toEntity());
        return UserResponseDTO.fromEntity(user);
    }

    @Override
    public void removeUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO retrieveUser(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        return UserResponseDTO.fromEntity(user);
    }

    @Override
    public List<UserResponseDTO> listAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::fromEntity).collect(Collectors.toList());
    }
}