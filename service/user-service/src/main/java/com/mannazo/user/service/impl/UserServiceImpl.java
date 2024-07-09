package com.mannazo.user.service.impl;

import com.mannazo.user.client.auth.AuthClient;
import com.mannazo.user.client.auth.LoginRequestDTO;
import com.mannazo.user.client.auth.LoginResponseDTO;
import com.mannazo.user.dto.UserRequestDTO;
import com.mannazo.user.dto.UserResponseDTO;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.mapStruct.UserRequestMapStruct;
import com.mannazo.user.mapStruct.UserResponseMapStruct;
import com.mannazo.user.repository.UserRepository;
import com.mannazo.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final AuthClient authClient;

    private final UserRepository userRepository;
    private final UserResponseMapStruct userResponseMapStruct;
    private final UserRequestMapStruct userRequsetMapStruct;

    @Override
    public LoginResponseDTO createUser(LoginRequestDTO loginRequestDTO, UserRequestDTO user) {
        // 유저 생성
        UserEntity userEntity = userRequsetMapStruct.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);

        // Auth-Service 소셜 정보 등록 요청
        log.info("인증 서비스에 소셜 정보 등록을 요청합니다.");
        authClient.save(loginRequestDTO.getSocialId(), savedEntity.getUserId());

        // Auth-Service 로그인 요청
        log.info("인증 서버의 로그인 요청 으로 리다이렉션");
        return authClient.login(loginRequestDTO).getBody();
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
