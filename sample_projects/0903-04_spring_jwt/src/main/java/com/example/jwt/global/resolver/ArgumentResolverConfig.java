package com.example.jwt.global.resolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ArgumentResolverConfig implements WebMvcConfigurer {
    @Bean
    public LoginUserArgumentResolver loginUserArgumentResolver() {
        return new LoginUserArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver());
    }
}
