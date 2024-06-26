package com.mannazo.mannazo.domain.account.repository;

import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findBySocialLoginId(String socialLoginId);

    List<UserEntity> findByLanguage(String language);
    List<UserEntity> findByGender(String gender);
    List<UserEntity> findByInterests(String interests);
}