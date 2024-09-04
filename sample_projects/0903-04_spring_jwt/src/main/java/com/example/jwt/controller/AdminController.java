package com.example.jwt.controller;

import com.example.jwt.model.dto.TodoDto;
import com.example.jwt.model.dto.UserDto;
import com.example.jwt.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = service.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
