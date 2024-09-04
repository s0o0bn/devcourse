package com.example.board.music.exception;

import com.example.board.global.exception.UnauthorizedException;

public class MusicAuthorizationException extends UnauthorizedException {
    public MusicAuthorizationException() {
        super("/musics?page=1");
    }
}
