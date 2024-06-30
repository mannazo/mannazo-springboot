package com.mannazo.mannazo.global.config;

import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.global.filter.JwtAuthFilter;
import com.mannazo.mannazo.global.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/user/**"
    };

    @PostConstruct
    public void init() {
        log.info("Spring Security Config Initialized");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //CSRF, CORS
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        //세션 관리 상태 없음으로 구성, Spring Security가 세션 생성 or 사용 X
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        //FormLogin, BasicHttp 비활성화
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);


        //JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthFilter(userService, jwtUtil), UsernamePasswordAuthenticationFilter.class);

//        http.exceptionHandling((exceptionHandling) -> exceptionHandling
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)
//        );

        // 권한 규칙 작성
        http.authorizeHttpRequests(authorize -> authorize
                        // AUTH_WHITELIST에 있는 경로에 대해서만 인증을 요구하고 나머지 경로에 대해서는 모두 허용
                        .requestMatchers(AUTH_WHITELIST).authenticated()
                        .anyRequest().permitAll()
        );

        return http.build();
    }


}