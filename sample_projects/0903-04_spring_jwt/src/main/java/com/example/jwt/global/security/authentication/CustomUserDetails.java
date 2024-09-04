package com.example.jwt.global.security.authentication;

import com.example.jwt.model.entity.Role;
import com.example.jwt.model.entity.UserEntity;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Builder
public class CustomUserDetails implements UserDetails {
    private String username;
    private Role role;

    public static UserDetails of(UserEntity user) {
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(Role.values())
                .filter(roleType -> roleType == this.role)
                .map(roleType -> (GrantedAuthority) roleType::getName)
                .toList();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }
}
