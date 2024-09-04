package com.example.board.music;

import com.example.board.global.pagination.Page;
import com.example.board.global.pagination.PagedItems;
import com.example.board.music.dto.ModifiableMusicDto;
import com.example.board.music.dto.MusicDetailDto;
import com.example.board.music.dto.MusicItemDto;
import com.example.board.music.entity.Music;
import com.example.board.music.exception.MusicAuthorizationException;
import com.example.board.music.exception.MusicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final MusicRepository repository;

    public PagedItems<MusicItemDto> getMusicList(Page page) {
        int offset = page.getOffset();
        int count = repository.count();
        List<Music> musics = repository.findAll(offset, Page.PAGE_SIZE);
        List<MusicItemDto> items = musics.stream()
                .map(MusicItemDto::from)
                .toList();

        return new PagedItems<>(items, count, page.page());
    }

    public MusicDetailDto getMusicDetail(int no) {
        Music music = repository.findByNo(no)
                .orElseThrow(MusicNotFoundException::new);
        return MusicDetailDto.from(music);
    }

    public void register(ModifiableMusicDto dto, String user) {
        Music music = Music.of(dto, user);
        repository.save(music);
    }

    public void update(String user, int no, ModifiableMusicDto dto) {
        Music music = repository.findByNo(no)
                .orElseThrow(MusicNotFoundException::new);

        if (music.isModifiable(user)) {
            throw new MusicAuthorizationException();
        }

        music.update(dto);
        repository.save(music);
    }

    public void delete(String user, int no) {
        Music music = repository.findByNo(no)
                .orElseThrow(MusicNotFoundException::new);

        if (music.isModifiable(user)) {
            throw new MusicAuthorizationException();
        }

        repository.delete(no);
    }
}
