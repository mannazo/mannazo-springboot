package com.mannazo.auth.service;

import com.mannazo.auth.client.user.UserClient;
import com.mannazo.auth.client.user.UserResponseDTO;
import com.mannazo.auth.dto.LoginRequestDTO;
import com.mannazo.auth.dto.LoginResponseDTO;
import com.mannazo.auth.dto.SocialDTO;
import com.mannazo.auth.entity.SocialEntity;
import com.mannazo.auth.repository.AuthRepository;
import com.mannazo.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Value("${google.secret}")
    private String GOOGLE_SECRET_KEY;
    @Value("${kakao.secret}")
    private String KAKAO_SECRET_KEY;
    @Value("${naver.secret}")
    private String NAVER_SECRET_KEY;

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;
    private final UserClient userClient;

    // 회원가입 여부 확인 매서드
    private Optional<SocialEntity> findBySocialId(String socialId) {
        // 소셜아이디로 회원가입 여부 확인
        Optional<SocialEntity> socialEntity = authRepository.findBySocialId(socialId);
        
        // 로깅 나중에 따로 빼야함
        if (socialEntity.isEmpty()) {
            log.info("{} 사용자에 대한 회원가입 내역이 없습니다.",socialId);
        }else {
            log.info("{} 사용자에 대한 회원가입 내역을 확인 했습니다.",socialId);
        }
        return socialEntity;
    }

    // 프로바이더 시크릿 키 검증 매서드
    private void verifySecret(String providedSecret, String secretKey, String errMsg) {
        if (!providedSecret.equals(secretKey)) {
            throw new IllegalArgumentException(errMsg);
        }
    }

    // 사용자 정보 확인 로그
    private void logFetchedUserInfo(String socialId, UserResponseDTO userResponseDTO) {
        log.info("{} 사용자 서비스에서 사용자 정보를 확인 했습니다. \n {}", socialId, userResponseDTO.toString());
    }

    //
    private void checkProviderValidity(String provider, String providedSecret) {
        switch (provider) {
            case "google":
                verifySecret(providedSecret, GOOGLE_SECRET_KEY, "구글 프로바이더 검증 실패");
                break;
            case "kakao":
                verifySecret(providedSecret, KAKAO_SECRET_KEY, "카카오 프로바이더 검증 실패");
                break;
            case "naver":
                verifySecret(providedSecret, NAVER_SECRET_KEY, "네이버 프로바이더 검증 실패");
                break;
            default:
                throw new IllegalArgumentException("Invalid provider");
        }
        log.info("프로바이더 검증을 완료했습니다.");
    }
    // 사용자
    private LoginResponseDTO createResponseForExistingUser(UserResponseDTO userResponseDTO) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setFirstTimeUser(false);
        loginResponseDTO.setUserId(userResponseDTO.getUserId());
        loginResponseDTO.setToken(jwtUtil.createAccessToken(userResponseDTO.getUserId()));
        log.info("클라이언트에 사용자 정보와 토큰을 전송 합니다.");
        return loginResponseDTO;
    }

    private LoginResponseDTO createResponseForNewUser(boolean firstTimeUser) {
        return new LoginResponseDTO(firstTimeUser, null, null);
    }

    @Override
    public SocialDTO saveSocialUser(SocialDTO socialDTO) {
        SocialEntity socialEntity = new SocialEntity();
        socialEntity.setUserid(socialDTO.getUserid());
        socialEntity.setSocialId(socialDTO.getSocialId());

        authRepository.save(socialEntity);
        
        log.info("소셜 테이블 등록 완료");
        return socialDTO;
    }

    // 사용자 로그인
    @Override
    public LoginResponseDTO getSocialLogin(LoginRequestDTO loginRequestDTO) {

        String socialId = loginRequestDTO.getSocialId();
        String provider = loginRequestDTO.getProvider();
        String providedSecret = loginRequestDTO.getSecret();

        // 공급자의 유효성 검사
        checkProviderValidity(provider, providedSecret);

        // 시스템에서 사용자 정보 가져오기
        Optional<SocialEntity> socialEntityOptional = findBySocialId(socialId);

        if (!socialEntityOptional.isPresent()) {
            log.info("클라이언트에 회원가입을 요청합니다.");
            return createResponseForNewUser(true);
        }

        UserResponseDTO userResponseDTOOptional = userClient.getUser(socialEntityOptional.get().getUserid())
                .orElseThrow(() -> new IllegalArgumentException("사용자 서비스에서 사용자를 찾을 수 없습니다."));

        // 가져온 사용자 정보 로깅
        logFetchedUserInfo(socialId, userResponseDTOOptional);

        return socialEntityOptional
                .map(socialEntity -> createResponseForExistingUser(userResponseDTOOptional))
                .orElseGet(() -> createResponseForNewUser(false));
    }
}
