package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;

public interface UserService {

    /**
     * 사용자 이름과 비밀번호로 로그인합니다.
     *
     * @param username
     * @param password
     */
    void login(String userid, String password);

    // 로그아웃 서비스
    void logout();

    // 유저 정보 수정
    void updateUserInformation(UserRequestDTO user);

    // 회원 가입
    void register(UserRequestDTO user);

    // 회원 탈퇴
    void withdraw(UserRequestDTO user);
}
