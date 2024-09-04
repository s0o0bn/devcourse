package com.example.jpa.service;

import com.example.jpa.domain.Board;
import com.example.jpa.domain.Comment;
import com.example.jpa.domain.dto.CommentDTO;
import com.example.jpa.repository.BoardRepository;
import com.example.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CommentDTO dto, int bno) {
        Board board = boardRepository.findById(bno).orElseThrow();
        Comment comment = Comment.builder()
                .content(dto.content())
                .writer(dto.writer())
                .board(board)
                .build();
        commentRepository.save(comment);
    }

    public List<CommentDTO> getComments(int bno) {
        return commentRepository.findByBno(bno);
    }

    public void deleteComment(int no) {
        commentRepository.deleteById(no);
    }
}
