package com.example.jwt.service;

import com.example.jwt.model.dto.TodoDto;
import com.example.jwt.model.dto.UserDto;
import com.example.jwt.repository.TodoRepository;
import com.example.jwt.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.SessionImpl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(TodoDto::from)
                .toList();
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::from)
                .toList();
    }
}
