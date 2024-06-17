package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.entity.User;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserInfoByUserid(UUID userid) {
        return null;
    }

    @Override
    public void updateUserInfo(UserRequestDTO user) {

    }

    @Override
    public void withdraw(UserRequestDTO user) {

    }
}
