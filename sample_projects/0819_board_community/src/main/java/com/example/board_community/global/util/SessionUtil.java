package com.example.board_community.global.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class SessionUtil {
    public static Optional<String> getUserIdFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return Optional.ofNullable((String) session.getAttribute("loginId"));
    }
}
