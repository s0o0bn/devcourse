package com.example.jwt.global.security.jwt.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS_TOKEN("Access-Token", 60),
    REFRESH_TOKEN("Refresh-Token", 2 * 60);

    private final String type;
    private final int expirationTime;
}
