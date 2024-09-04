package com.example.jwt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String name;

    public static Role valueOfName(String type) {
        return Arrays.stream(Role.values())
                .filter(role -> role.name().equals(type))
                .findAny()
                .orElse(null);
    }
}
