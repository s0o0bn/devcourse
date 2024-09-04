package com.example.board.global.web;

import com.example.board.user.exception.UnauthenticatedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath();
        if (url.equals("/musics") && request.getMethod().equals("GET")) {
            return true;
        }

        String loginId = (String) request.getSession().getAttribute("loginId");
        if (loginId == null) {
            throw new UnauthenticatedException();
        }
        return true;
    }
}
