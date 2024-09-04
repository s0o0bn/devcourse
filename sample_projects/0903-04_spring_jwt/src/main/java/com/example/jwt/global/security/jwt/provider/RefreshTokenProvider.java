package com.example.jwt.global.security.jwt.provider;

import com.example.jwt.global.security.jwt.consts.TokenType;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefreshTokenProvider extends AbstractJwtTokenProvider {
    @Override
    public String generateToken(String payload) {
        return Jwts.builder()
                .expiration(calcExpirationTime(TokenType.REFRESH_TOKEN))
                .subject(payload)
                .issuedAt(new Date())
                .issuer("app_demo")
                .signWith(signKey)
                .compact();
    }

    @Override
    public boolean isTokenExpired(String token) {
        return parseClaimsFromToken(token).getExpiration().before(new Date());
    }
}
