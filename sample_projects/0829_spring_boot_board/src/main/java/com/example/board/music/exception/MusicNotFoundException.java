package com.example.board.music.exception;

import com.example.board.global.exception.NotFoundException;

public class MusicNotFoundException extends NotFoundException {
    public MusicNotFoundException() {
        super("존재하지 않는 음악입니다.", "/musics?page=1");
    }
}
