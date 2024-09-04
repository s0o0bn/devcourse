package com.example.board.global.exception;

public class NotFoundException extends AppException {
    public NotFoundException(String message, String path) {
        super(message, path);
    }
}
