package com.example.board.infra.mybatis.repository;

import com.example.board.comment.CommentRepository;
import com.example.board.infra.mybatis.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisCommentRepository implements CommentRepository {
    private final CommentMapper mapper;
}
