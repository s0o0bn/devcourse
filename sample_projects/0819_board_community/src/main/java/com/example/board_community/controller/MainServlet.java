package com.example.board_community.controller;

import com.example.board_community.global.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/main.do")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> loginId = SessionUtil.getUserIdFromSession(req);

        req.setAttribute("loginId", loginId);
        req.getRequestDispatcher("WEB-INF/views/main.jsp").forward(req, resp);
    }
}
