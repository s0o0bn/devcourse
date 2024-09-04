package com.example.jwt.global.security.jwt.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthConstant {
    AUTH_HEADER("Authorization"),
    AUTH_TOKEN_PREFIX("Bearer ");

    private final String value;
}
