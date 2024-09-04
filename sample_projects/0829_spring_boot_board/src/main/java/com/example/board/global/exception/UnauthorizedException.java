package com.example.board.global.exception;

public class UnauthorizedException extends AppException {
    public UnauthorizedException() {
        this("/");
    }

    public UnauthorizedException(String path) {
        super("권한이 없습니다.", path);
    }
}
