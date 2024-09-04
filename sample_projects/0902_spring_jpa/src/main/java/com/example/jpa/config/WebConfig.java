package com.example.jpa.config;

import com.example.jpa.global.web.LoginUserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LoginUserInterceptor loginUserInterceptor() {
        return new LoginUserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/member/**", "/main", "/error");
    }
}
