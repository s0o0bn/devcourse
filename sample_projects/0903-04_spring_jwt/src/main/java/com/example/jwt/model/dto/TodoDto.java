package com.example.jwt.model.dto;

import com.example.jwt.model.entity.TodoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TodoDto(int id, @NotBlank String title, boolean isDone) {
    public static TodoDto from(TodoEntity entity) {
        return TodoDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .isDone(entity.isDone())
                .build();
    }

    public TodoEntity toEntity(String username) {
        return TodoEntity.builder()
                .username(username)
                .title(title)
                .isDone(isDone)
                .build();
    }
}
