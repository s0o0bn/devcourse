package com.example.jwt.global.security;

import com.example.jwt.global.security.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

//@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final JwtTokenFilter jwtTokenFilter;

//    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterFilterRegistrationBean() {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(jwtTokenFilter);
        registrationBean.addUrlPatterns("/todos/*");

        return registrationBean;
    }
}
