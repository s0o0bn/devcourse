package com.example.jwt.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String username;
    @NotNull
    private String password;
    private String refreshToken;
    private String authProvider;

    @Enumerated(EnumType.STRING)
    private Role role;
}
