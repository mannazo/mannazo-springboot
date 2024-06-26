package com.mannazo.mannazo.domain.login.service;

import ch.qos.logback.core.net.server.Client;
import com.mannazo.mannazo.domain.login.dto.KakaoUserInfoResponseDto;

import java.util.UUID;

public interface SocialLoginService<DTO> {

    /**
     * 인증 코드 발급 서비스를 위한 리디렉션 URL을 반환합니다.
     *
     * @return 리디렉션 URL
     */
    String getRedirectUrl();

    /**
     * 인증 코드를 통해서 엑세스(사용자)토큰을 발급하는 메서드입니다.(Kakao)
     *
     * @param AuthCode 인증 코드
     * @return 발급된 사용자 토큰
     */
    String getAccessToken(String AuthCode);

    /**
     * 인증 코드를 통해서 엑세스(사용자)토큰을 발급하는 메서드입니다.(Naver)
     *
     * @param code 인증 코드
     * @param state 보안을 위한 무작위 임의값
     * @return 발급된 사용자 토큰
     */
    String getAccessToken(String code, String state);

    /**
     * 액세스 토큰을 사용하여 사용자 정보를 검색합니다.
     *
     * @param accessToken 사용자 정보를 검색하기 위한 액세스 토큰
     * @return T 타입의 사용자 정보
     */
    DTO getUserInfo(String accessToken);
}