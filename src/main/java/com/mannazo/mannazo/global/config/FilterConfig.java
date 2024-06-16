package com.mannazo.mannazo.global.config;

import com.mannazo.mannazo.global.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 URL에 대해 필터를 적용
        registrationBean.setOrder(1); // 필터의 순서 설정 (여러 필터가 있을 경우 우선순위)
        return registrationBean;
    }
}