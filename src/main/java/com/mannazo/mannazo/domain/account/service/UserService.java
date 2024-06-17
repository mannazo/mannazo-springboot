package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    /**
     * 유저 아이디(UUID) 를 요청하면 유저의 전체 정보를 반환하는 서비스
     *
     * @param userid
     */
    // 유저 정보 조회
    Optional<User> getUserInfoByUserid(UUID userid);
    // 유저 정보 수정
    void updateUserInfo(UserRequestDTO user);
    // 회원 탈퇴
    void withdraw(UserRequestDTO user);
    //
}
