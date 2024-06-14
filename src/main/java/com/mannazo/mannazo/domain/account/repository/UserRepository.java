package com.mannazo.mannazo.domain.account.repository;

import com.mannazo.mannazo.domain.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
