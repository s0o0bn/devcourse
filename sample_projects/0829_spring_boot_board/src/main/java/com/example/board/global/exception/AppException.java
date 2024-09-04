package com.example.board.global.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final String message;
    private final String path;

    public AppException(String message, String path) {
        this.message = message;
        this.path = path;
    }
}
