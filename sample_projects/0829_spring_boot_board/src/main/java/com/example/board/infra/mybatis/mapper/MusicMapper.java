package com.example.board.infra.mybatis.mapper;

import com.example.board.music.entity.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {
    List<Music> selectAll(int offset, int limit);

    Music selectByNo(int no);

    int count();

    void insert(Music music);

    void update(Music music);

    void delete(int no);
}
