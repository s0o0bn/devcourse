package com.example.jwt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@AllArgsConstructor
@RedisHash("Token")
public class Token {
    @Id
    private String username;
    private String refreshToken;
    @TimeToLive
    private long ttl;

    public boolean isValidToken(String token) {
        return refreshToken.equals(token);
    }
}
