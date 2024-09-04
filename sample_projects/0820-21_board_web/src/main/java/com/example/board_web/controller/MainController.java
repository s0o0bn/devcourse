package com.example.board_web.controller;

import com.example.board_web.controller.util.MyPageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController implements MyController {
    @Override
    public Object handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        return new MyPageInfo("/main", true);
    }
}
