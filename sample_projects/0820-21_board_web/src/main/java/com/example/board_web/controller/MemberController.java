package com.example.board_web.controller;

import com.example.board_web.controller.util.MyPageInfo;
import com.example.board_web.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public class MemberController implements MyController {
    public static final String PREFIX = "/member";
    private final MemberService service = MemberService.getInstance();

    @Override
    public Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String url = req.getServletPath();

        if (url.equals(PREFIX + "/loginForm.do")) {
            return new MyPageInfo("/login_form", true);
        }

        if (url.equals(PREFIX + "/login.do")) {
            String id = req.getParameter("userId");
            String pw = req.getParameter("password");
            String loginId = service.login(id, pw);
            if (loginId == null) {
                req.setAttribute("msg", "로그인 실패.");
                req.setAttribute("path", req.getContextPath() + PREFIX + "/loginForm.do");
                return new MyPageInfo("/alert", true);
            }

            HttpSession session = req.getSession();
            session.setAttribute("loginId", loginId);
            req.setAttribute("msg", "로그인 성공.");
            req.setAttribute("path", req.getContextPath() + "/main.do");
            return new MyPageInfo("/alert", true);
        }

        if (url.equals(PREFIX + "/logout.do")) {
            // * invalidate : Map.clear() ; 전체 삭제
            // * removeAttribute : Map.remove() ; 단일 삭제
            req.getSession().invalidate();
            req.setAttribute("msg", "로그아웃 성공.");
            req.setAttribute("path", req.getContextPath() + "/main.do");
            return new MyPageInfo("/alert", true);
        }

        return null;
    }
}
