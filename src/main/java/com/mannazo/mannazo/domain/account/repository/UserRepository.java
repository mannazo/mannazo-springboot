package com.mannazo.mannazo.domain.account.repository;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}