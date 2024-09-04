package com.example.jpa.service;

import com.example.jpa.domain.User;
import com.example.jpa.domain.dto.SignupDTO;
import com.example.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public String login(String userId, String password) {
        User user = repository.findById(userId).orElseThrow();
        if (!user.isPasswordValid(password)) {
            throw new RuntimeException("no authenticated");
        }
        return userId;
    }

    public void signup(SignupDTO params) {
        User user = User.builder()
                .id(params.userId())
                .password(params.password())
                .name(params.name())
                .build();
        repository.save(user);
    }
}
