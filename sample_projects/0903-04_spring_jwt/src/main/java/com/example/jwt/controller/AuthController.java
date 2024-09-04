package com.example.jwt.controller;

import com.example.jwt.global.security.jwt.consts.TokenType;
import com.example.jwt.model.dto.TokenDto;
import com.example.jwt.model.dto.UserDto;
import com.example.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto dto) {
        return new ResponseEntity<>(service.signup(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserDto dto) {
        TokenDto tokens = service.login(dto);
        ResponseCookie cookie = createRefreshTokenCookie(tokens.refreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header("Authorization", tokens.accessToken())
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshToken(@CookieValue("Refresh-Token") String refreshToken) {
        TokenDto tokens = service.refresh(refreshToken);
        ResponseCookie cookie = createRefreshTokenCookie(tokens.refreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header("Authorization", tokens.accessToken())
                .build();
    }

    private ResponseCookie createRefreshTokenCookie(String token) {
        return ResponseCookie.from(TokenType.REFRESH_TOKEN.getType(), token)
                .path("/")
                .httpOnly(true)
                .maxAge(TokenType.REFRESH_TOKEN.getExpirationTime())
                .sameSite("None")
                .build();
    }
}
