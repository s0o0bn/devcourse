package com.example.jwt.global.security.jwt.provider;

import com.example.jwt.global.security.jwt.consts.AuthConstant;

public interface JwtTokenProvider {
    String generateToken(String payload);

    String parseTokenPayload(String token);

    boolean isTokenExpired(String token);

    default String resolveTokenFromHeader(String header) {
        return parseTokenPayload(header.substring(AuthConstant.AUTH_TOKEN_PREFIX.getValue().length()));
    }
}
