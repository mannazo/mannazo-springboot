package com.mannazo.auth.repository;

import com.mannazo.auth.entity.SocialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<SocialEntity, UUID> {
    Optional<SocialEntity> findBySosialId(String sosialId);
}
