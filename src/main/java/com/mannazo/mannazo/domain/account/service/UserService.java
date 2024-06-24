package com.mannazo.mannazo.domain.account.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * 사용자가 입력한 DTO를 받아서 회원가입하는 서비스
     * @param userRequestDTO 입력값
     * @return UserResponseDTO 저장된 회원정보
     */
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    /**
     * 사용자가 입력한 DTO받아서 회원수정하는 서비스(추가입력사항 등록)
     * @param userRequestDTO 입력값
     * @return UserResponseDTO 저장된 회원정보
     */
    UserResponseDTO modifyUserDetails(UserRequestDTO userRequestDTO);

    /**
     * 사용자 user_id를 받아서 해당 회원 정보 삭제
     * @param id 회원의 고유 user_id
     * @return 삭제된 user_id와 삭제 메시지
     */
    UserResponseDTO.Delete removeUser(UUID id);

    /**
     * 사용자 user_id를 받아서 해당하는 사용자 정보 제공
     * @param id 회원의 고유 user_id
     * @return UserResponseDTO 저장된 회원정보
     */
    UserResponseDTO retrieveUser(UUID id);

    // 모든 사용자 목록 조회
    List<UserResponseDTO> listAllUsers();

    /**
     * socialLoginId로 user_id 조회
     * @param socialLoginId 저장된 socialLoginId
     * @return UUID user_id
     */
    UUID getUserIdBySocialLoginId(String socialLoginId);
}