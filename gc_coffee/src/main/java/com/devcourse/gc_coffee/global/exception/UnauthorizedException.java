package com.devcourse.gc_coffee.global.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("You have no authorization.");
    }
}
