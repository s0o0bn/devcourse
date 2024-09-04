package com.example.board_community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/alert.do")
public class AlertServlet extends HttpServlet {
    public static final String BOARD_LIST_URI = "/board.do?action=list";
    public static final String LOGIN_URI = "/auth.do?action=login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("login")) {
            req.setAttribute("msg", "로그인이 필요합니다.");
            req.setAttribute("path", req.getContextPath() + LOGIN_URI);
        } else if (action.equals("write")) {
            req.setAttribute("msg", "글 작성이 완료되었습니다.");
            req.setAttribute("path", req.getContextPath() + BOARD_LIST_URI);
        } else if (action.equals("delete")) {
            req.setAttribute("msg", "글이 삭제되었습니다.");
            req.setAttribute("path", req.getContextPath() + BOARD_LIST_URI);
        } else if (action.equals("not-found")) {
            req.setAttribute("msg", "존재하지 않는 글입니다.");
            req.setAttribute("path", req.getContextPath() + BOARD_LIST_URI);
        }

        req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
    }
}
