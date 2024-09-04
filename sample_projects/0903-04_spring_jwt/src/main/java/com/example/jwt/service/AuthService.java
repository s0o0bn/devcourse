package com.example.jwt.service;

import com.example.jwt.global.security.jwt.provider.JwtTokenProvider;
import com.example.jwt.global.security.jwt.consts.TokenType;
import com.example.jwt.model.dto.TokenDto;
import com.example.jwt.model.dto.UserDto;
import com.example.jwt.model.entity.Token;
import com.example.jwt.model.entity.UserEntity;
import com.example.jwt.repository.TokenRepository;
import com.example.jwt.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider accessTokenProvider;
    private final JwtTokenProvider refreshTokenProvider;

    @Transactional
    public UserDto signup(@NonNull UserDto dto) {
        UserEntity user = dto.toEntity(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
        return UserDto.from(user);
    }

    @Transactional(readOnly = true)
    public TokenDto login(@NonNull UserDto dto) {
        UserEntity user = userRepository.findById(dto.username()).orElseThrow();
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new IllegalArgumentException("authentication failure");
        }

        // Refresh Token Rotation
        tokenRepository.deleteById(user.getUsername());
        return generateTokens(user.getUsername());
    }

    public TokenDto refresh(@NonNull String refreshToken) {
        if (refreshTokenProvider.isTokenExpired(refreshToken)) {
            throw new IllegalArgumentException("Refresh token expired");
        }

        String username = refreshTokenProvider.parseTokenPayload(refreshToken);
        Optional<Token> redisToken = tokenRepository.findById(username);

        // Reuse Detection (탈취된 이전 refresh token 사용 시도 감지)
        if (redisToken.isEmpty() || !redisToken.get().isValidToken(refreshToken)) {
            // 정상 refresh token invalidate 해야 함
            tokenRepository.deleteById(username);
            throw new IllegalArgumentException("access denied");
        }

        return generateTokens(username);
    }

    private TokenDto generateTokens(String username) {
        String accessToken = accessTokenProvider.generateToken(username);
        String refreshToken = refreshTokenProvider.generateToken(username);
        Token token = new Token(username, refreshToken, TokenType.REFRESH_TOKEN.getExpirationTime());
        tokenRepository.save(token);

        return new TokenDto(accessToken, refreshToken);
    }
}
