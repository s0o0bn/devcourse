package com.example.jwt.global.security.jwt.provider;

import com.example.jwt.global.security.jwt.consts.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public abstract class AbstractJwtTokenProvider implements JwtTokenProvider {
    // environment
//    private final String secret = "your=64=byte=long=base64=encoded=key=====1234";
    protected final SecretKey signKey;

    protected AbstractJwtTokenProvider() {
        this.signKey = getTokenSecret();
    }

    public String parseTokenPayload(String token) {
        Claims claims = parseClaimsFromToken(token);
        return claims.getSubject();
    }

    protected Date calcExpirationTime(TokenType type) {
        long ttl = type.getExpirationTime() + 1000L;
        return new Date(System.currentTimeMillis() + ttl);
    }

    protected Claims parseClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getTokenSecret() {
//        byte[] secretBytes = Decoders.BASE64.decode(secret);
//        return Keys.hmacShaKeyFor(secretBytes);
        return Jwts.SIG.HS512.key().build();
    }
}
