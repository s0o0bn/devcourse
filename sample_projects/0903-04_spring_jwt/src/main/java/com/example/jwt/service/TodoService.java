package com.example.jwt.service;

import com.example.jwt.model.dto.TodoDto;
import com.example.jwt.model.entity.TodoEntity;
import com.example.jwt.repository.TodoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    @Transactional
    public List<TodoDto> create(@NonNull TodoDto dto, @NonNull String username) {
        TodoEntity todo = dto.toEntity(username);
        repository.save(todo);

        return repository.findByUsername(username).stream()
                .map(TodoDto::from)
                .toList();
    }

    @Transactional
    public TodoDto update(int id, @NonNull TodoDto dto) {
        TodoEntity todo = repository.findById(id)
                        .orElseThrow(NoSuchElementException::new);
        todo.update(dto);
        repository.save(todo);
        return TodoDto.from(todo);
    }

    @Transactional(readOnly = true)
    public List<TodoDto> getTodo(@NonNull String username) {
        return repository.findByUsername(username).stream()
                .map(TodoDto::from)
                .toList();
    }
}
