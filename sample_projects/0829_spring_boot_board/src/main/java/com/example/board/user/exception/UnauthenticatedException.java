package com.example.board.user.exception;

import com.example.board.global.exception.AppException;

public class UnauthenticatedException extends AppException {
    public UnauthenticatedException() {
        super("로그인이 필요합니다.", "/auth/login-form");
    }
}
