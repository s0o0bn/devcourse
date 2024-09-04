package com.example.jwt.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleGlobalException(RuntimeException e) {
//        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
