package com.mannazo.user.service.impl;

import com.mannazo.user.client.auth.AuthClient;
import com.mannazo.user.client.auth.LoginRequestDTO;
import com.mannazo.user.client.auth.LoginResponseDTO;
import com.mannazo.user.client.auth.SocialDTO;
import com.mannazo.user.dto.UserCreationRequestDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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
    public LoginResponseDTO createUser(UserCreationRequestDTO newUser) {
        LoginRequestDTO loginRequestDTO = newUser.getLoginRequestDTO();
        UserRequestDTO userRequestDTO = newUser.getUserRequestDTO();

        // 유저 생성
        log.info("유저 생성을 진행합니다");
        UserEntity userEntity = userRequsetMapStruct.toEntity(userRequestDTO);
        UserEntity savedEntity = userRepository.save(userEntity);
        log.info("유저 생성 완료");

        // Auth-Service 소셜 정보 등록 요청
        log.info("인증 서비스에 소셜 정보 등록을 요청합니다.");
        SocialDTO socialDTO = new SocialDTO();
        socialDTO.setUserid(savedEntity.getUserId());
        socialDTO.setSocialId(loginRequestDTO.getSocialId());

        authClient.save(socialDTO);
        log.info("소셜 정보 등록 완료");

        // Auth-Service 로그인 요청
        log.info("인증 서버의 로그인 요청 으로 리다이렉션");
        return authClient.login(loginRequestDTO);
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
        log.info("데이터베이스에서 유저를 삭제합니다 \n");
        userRepository.deleteById(userId);
        // Auth-Service 소셜 정보 삭제 요청
        log.info("소셜 로그인 정보를 삭제합니다. \n");
        authClient.delete(userId);
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(UUID userId, UserRequestDTO user) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRequsetMapStruct.updateUserFromDto(user, userEntity);
        userRepository.save(userEntity);
        return userResponseMapStruct.toUserResponseDTO(userEntity);
    }

    @Override
    public int getNumberOfUsers(String nationality) {
        List<UserEntity> list = userRepository.findByNationality(nationality);
        return list.size();
    }

    @Override
    public int getNumberOfAllUsers() {
        List<UserEntity> list = userRepository.findAll();
        return list.size();
    }

    @Override
    public Map<UUID, UserResponseDTO> getUsers(List<UUID> userIds) {
        List<UserEntity> list = userRepository.findAllById(userIds);
        List<UserResponseDTO> userResponseDTOS = userResponseMapStruct.toUserResponseListDTO(list);
        return userResponseMapStruct.toUserResponseMap(userResponseDTOS);
    }

    @Override
    public List<UUID> getAllUserIds() {
        List<UserEntity> list = userRepository.findAll();
        return list.stream().map(UserEntity::getUserId).toList();
    }
}
