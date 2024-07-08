package com.mannazo.user.service.impl;

import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.mapStruct.UserRequestMapStruct;
import com.mannazo.user.mapStruct.UserResponseMapStruct;
import com.mannazo.user.repository.UserRepository;
import com.mannazo.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapStruct userResponseMapStruct;
    private final UserRequestMapStruct userRequsetMapStruct;

    @Override
    public UserResponseDTO createUser(UserRequestDTO user) {
        UserEntity userEntity = userRequsetMapStruct.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userResponseMapStruct.toUserResponseDTO(savedEntity);
    }

    @Override
    public UserResponseDTO getUser(UUID userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        UserResponseDTO userInfo = userResponseMapStruct.toUserResponseDTO(userEntity.orElse(null));
        return userInfo;
    }

    @Override
    public List<UserResponseDTO> findAll() {

        List<UserEntity> list = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = userResponseMapStruct.toUserResponseListDTO(list);

        return userResponseDTOS;
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDTO updateUser(UUID userId, UserRequestDTO user) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRequsetMapStruct.updateUserFromDto(user, userEntity);
        userRepository.save(userEntity);
        return userResponseMapStruct.toUserResponseDTO(userEntity);
    }
}
