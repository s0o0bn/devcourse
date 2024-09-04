package com.example.jwt.repository;

import com.example.jwt.model.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    List<TodoEntity> findByUsername(String username);
}
