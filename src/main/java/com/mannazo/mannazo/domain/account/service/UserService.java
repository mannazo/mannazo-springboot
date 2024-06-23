package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    // 사용자 등록
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    // 사용자 상세 정보 수정
    UserResponseDTO modifyUserDetails(UserRequestDTO userRequestDTO);

    // 사용자 제거
    UserResponseDTO.Delete removeUser(UUID id);

    // 사용자 정보 검색
    UserResponseDTO retrieveUser(UUID id);

    // 모든 사용자 목록 조회
    List<UserResponseDTO> listAllUsers();

    UUID getUserIdBySocialLoginId(String socialLoginId);
}