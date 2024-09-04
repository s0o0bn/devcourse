package com.example.jwt.model.entity;

import com.example.jwt.model.dto.TodoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "todo")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String username;
    private String title;
    private boolean isDone;

    public void update(TodoDto dto) {
        this.title = dto.title();
        this.isDone = dto.isDone();
    }
}
