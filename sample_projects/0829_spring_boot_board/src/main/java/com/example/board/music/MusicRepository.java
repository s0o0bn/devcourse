package com.example.board.music;

import com.example.board.music.entity.Music;

import java.util.List;
import java.util.Optional;

//@Repository
public interface MusicRepository {
    List<Music> findAll(int offset, int limit);

    Optional<Music> findByNo(int no);

    int count();

    void save(Music music);

    void delete(int no);
}
