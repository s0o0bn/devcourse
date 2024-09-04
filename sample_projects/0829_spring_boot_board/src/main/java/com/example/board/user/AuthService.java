package com.example.board.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;

    public String login(String username, String password) {
        User user = repository.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean isValid = user.isValidPassword(password);
        if (!isValid) {
            throw new RuntimeException("Authentication failure");
        }

        return username;
    }
}
