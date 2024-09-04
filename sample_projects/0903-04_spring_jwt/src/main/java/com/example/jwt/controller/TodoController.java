package com.example.jwt.controller;

import com.example.jwt.model.dto.TodoDto;
import com.example.jwt.global.resolver.LoginUser;
import com.example.jwt.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getTodoList(@LoginUser String user) {
        List<TodoDto> todos = service.getTodo(user);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<List<TodoDto>> createTodo(@LoginUser String user, @RequestBody TodoDto dto) {
        List<TodoDto> todos = service.create(dto, user);
        return new ResponseEntity<>(todos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable int id, @RequestBody TodoDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
