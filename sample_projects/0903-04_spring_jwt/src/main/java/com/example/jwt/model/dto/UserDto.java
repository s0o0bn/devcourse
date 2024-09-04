package com.example.jwt.model.dto;

import com.example.jwt.model.entity.Role;
import com.example.jwt.model.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String username, @NotBlank String password, String role) {
    public static UserDto from(UserEntity entity) {
        return new UserDto(entity.getUsername(), entity.getPassword(), entity.getRole().getName());
    }

    public UserEntity toEntity(String encryptedPassword) {
        return UserEntity.builder()
                .username(username)
                .password(encryptedPassword)
                .role(Role.valueOfName(role))
                .build();
    }
}
