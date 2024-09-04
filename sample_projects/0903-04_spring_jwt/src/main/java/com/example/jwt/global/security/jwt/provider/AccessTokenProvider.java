package com.example.jwt.global.security.jwt.provider;

import com.example.jwt.global.security.jwt.consts.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AccessTokenProvider extends AbstractJwtTokenProvider {
    @Override
    public String generateToken(String payload) {
        return Jwts.builder()
                .expiration(calcExpirationTime(TokenType.ACCESS_TOKEN))
                .subject(payload)
                .issuedAt(new Date())
                .issuer("app_demo")
                .signWith(signKey)
                .compact();
    }

    @Override
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = parseClaimsFromToken(token);
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }
}
