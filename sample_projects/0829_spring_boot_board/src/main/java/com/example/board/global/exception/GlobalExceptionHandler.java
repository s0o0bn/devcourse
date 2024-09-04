package com.example.board.global.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ModelAndView handleRuntimeException(AppException e) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("msg", e.getMessage());
        mv.addObject("path", e.getPath());
        return mv;
    }
}
