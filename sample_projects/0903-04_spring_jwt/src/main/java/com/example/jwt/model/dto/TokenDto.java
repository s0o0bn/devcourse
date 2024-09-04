package com.example.jwt.model.dto;

import lombok.NonNull;

public record TokenDto(@NonNull String accessToken, @NonNull String refreshToken) {
}
