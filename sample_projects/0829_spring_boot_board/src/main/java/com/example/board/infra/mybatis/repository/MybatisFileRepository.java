package com.example.board.infra.mybatis.repository;

import com.example.board.infra.mybatis.mapper.FileMapper;
import com.example.board.music.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MybatisFileRepository implements FileRepository {
    private final FileMapper mapper;
}
