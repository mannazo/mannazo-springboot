package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.entity.User;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void login(String userid, String password) {
        User userEntity = userRepository.findByUserIdAndPassword(userid, password);
    }

    @Override
    public void logout() {

    }

    @Override
    public void updateUserInformation(UserRequestDTO user) {

    }

    @Override
    public void register(UserRequestDTO user) {

    }

    @Override
    public void withdraw(UserRequestDTO user) {

    }
}
