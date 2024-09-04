package com.example.board.infra.mybatis.repository;

import com.example.board.infra.mybatis.mapper.MusicMapper;
import com.example.board.music.MusicRepository;
import com.example.board.music.entity.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisMusicRepository implements MusicRepository {
    private final MusicMapper mapper;

    @Override
    public List<Music> findAll(int offset, int limit) {
        return mapper.selectAll(offset, limit);
    }

    @Override
    public Optional<Music> findByNo(int no) {
        return Optional.ofNullable(mapper.selectByNo(no));
    }

    @Override
    public int count() {
        return mapper.count();
    }

    @Override
    public void save(Music music) {
        if (music.getNo() == null) {
            mapper.insert(music);
        } else {
            mapper.update(music);
        }
    }

    @Override
    public void delete(int no) {
        mapper.delete(no);
    }
}
