package com.example.jpa.repository;

import com.example.jpa.domain.Comment;
import com.example.jpa.domain.dto.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.board WHERE c.board.no=:bno")
    List<CommentDTO> findByBno(int bno);
}
