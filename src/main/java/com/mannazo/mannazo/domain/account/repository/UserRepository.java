package com.mannazo.mannazo.domain.account.repository;

import com.mannazo.mannazo.domain.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserIdAndPassword(String userid, String password);
}