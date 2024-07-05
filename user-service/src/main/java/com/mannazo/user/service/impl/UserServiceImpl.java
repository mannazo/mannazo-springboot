package com.mannazo.user.service.impl;

import com.mannazo.user.domain.UserRequestDTO;
import com.mannazo.user.domain.UserResponseDTO;
import com.mannazo.user.entity.Authority;
import com.mannazo.user.entity.Gender;
import com.mannazo.user.entity.UserEntity;
import com.mannazo.user.repository.UserRepository;
import com.mannazo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getUser(UUID userId) {
        Optional<UserEntity> OptionalEntity = userRepository.findById(userId);
        UserEntity entity = OptionalEntity.get();

        UserResponseDTO user = new UserResponseDTO();

        user.setUserId(entity.getUserId());
        user.setEmail(entity.getEmail());
        user.setName(entity.getName());
        user.setNickname(entity.getNickname());
        user.setNationality(entity.getNationality());
        user.setLanguage(entity.getLanguage());
        user.setProfileImage(entity.getProfileImage());
        user.setIntroduction(entity.getIntroduction());
        user.setCity(entity.getCity());
        user.setAuthority(String.valueOf(entity.getAuthority()));
        user.setGender(String.valueOf(entity.getGender()));
        user.setMbti(entity.getMbti());
        user.setInterests(entity.getInterests());
        user.setBirthday(String.valueOf(entity.getBirthday()));
        user.setLastLoginAt(String.valueOf(entity.getLastLoginAt()));

        return user;
    }

    @Override
    public List<UserResponseDTO> findAll() {

        List<UserEntity> list = userRepository.findAll();

        return list.stream()
                .map(entity -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setUserId(entity.getUserId());
                    dto.setEmail(entity.getEmail());
                    dto.setName(entity.getName());
                    dto.setNickname(entity.getNickname());
                    dto.setNationality(entity.getNationality());
                    dto.setLanguage(entity.getLanguage());
                    dto.setProfileImage(entity.getProfileImage());
                    dto.setIntroduction(entity.getIntroduction());
                    dto.setCity(entity.getCity());
                    dto.setAuthority(String.valueOf(entity.getAuthority()));
                    dto.setGender(String.valueOf(entity.getGender()));
                    dto.setMbti(entity.getMbti());
                    dto.setInterests(entity.getInterests());
                    dto.setBirthday(String.valueOf(entity.getBirthday()));
                    dto.setLastLoginAt(String.valueOf(entity.getLastLoginAt()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(UserRequestDTO user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(user.getUserId());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setNickname(user.getNickname());
        userEntity.setNationality(user.getNationality());
        userEntity.setLanguage(user.getLanguage());
        userEntity.setProfileImage(user.getProfileImage());
        userEntity.setIntroduction(user.getIntroduction());
        userEntity.setCity(user.getCity());
        userEntity.setAuthority(Authority.valueOf(user.getAuthority()));
        userEntity.setGender(Gender.valueOf(user.getGender()));
        userEntity.setMbti(user.getMbti());
        userEntity.setInterests(user.getInterests());
        userEntity.setBirthday(LocalDate.parse(user.getBirthday()));
        userEntity.setLastLoginAt(Timestamp.valueOf(user.getLastLoginAt()));

        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(UUID userId) {

        userRepository.deleteById(userId);

    }

    @Override
    public void updateUser(UUID userId, UserRequestDTO user) {

        UserEntity userEntity = userRepository.findById(userId).orElse(null);

        userEntity.setUserId(user.getUserId());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setNickname(user.getNickname());
        userEntity.setNationality(user.getNationality());
        userEntity.setLanguage(user.getLanguage());
        userEntity.setProfileImage(user.getProfileImage());
        userEntity.setIntroduction(user.getIntroduction());
        userEntity.setCity(user.getCity());
        userEntity.setAuthority(Authority.valueOf(user.getAuthority()));
        userEntity.setGender(Gender.valueOf(user.getGender()));
        userEntity.setMbti(user.getMbti());
        userEntity.setInterests(user.getInterests());

    }
}
