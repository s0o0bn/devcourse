package com.example.board_community.controller;

import com.example.board_community.global.exception.UnauthorizedException;
import com.example.board_community.global.exception.UserNotFoundException;
import com.example.board_community.model.user.AuthService;
import com.example.board_community.model.user.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth.do")
public class AuthServlet extends HttpServlet {
    private AuthService service = AuthService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action.equals("login")) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember-me")) {
                    req.setAttribute("userId", cookie.getValue());
                }
            }
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else if (action.equals("logout")) {
            HttpSession session = req.getSession();
            session.invalidate();

            req.setAttribute("msg", "로그아웃 되었습니다.");
            req.setAttribute("path", req.getContextPath());
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
        } else if (action.equals("signup")) {
            req.getRequestDispatcher("/WEB-INF/views/signupForm.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        boolean rememberMe = req.getParameter("remember-me") != null;
        String action = req.getParameter("action");

        try {
            if (action.equals("login")) {
                boolean isValidUser = service.login(userId, password);
                if (isValidUser) {
                    HttpSession session = req.getSession(); // 지금 요청보낸 사용자 전용 객체를 찾아낼 수 있음(이전에 나한테 JSESSIONID 쿠키 발급받아 갔을거임.)
                    session.setAttribute("loginId", userId);
                    if (rememberMe) {
                        Cookie cookie = new Cookie("remember-me", userId);
                        resp.addCookie(cookie);
                    }

                    req.setAttribute("msg", "로그인 완료되었습니다.");
                    req.setAttribute("path", req.getContextPath()); // 맨 첫페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }
            } else if (action.equals("signup")) {
                String name = req.getParameter("name");
                UserDTO user = new UserDTO(name, userId, password);
                int result = service.signup(user);
                if (result == 1) {
                    req.setAttribute("msg", "회원가입 되었습니다.");
                    req.setAttribute("path", req.getContextPath()); // 맨 첫페이지로 보냄
                    req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
        } catch (UserNotFoundException e) {
            req.setAttribute("msg", "존재하지 않는 아이디입니다. 회원가입 해주세요.");
            req.setAttribute("path", req.getContextPath() + "/auth.do?action=signup"); // 맨 첫페이지로 보냄
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
        } catch (UnauthorizedException e) {
            // login fail
            req.setAttribute("msg", "로그인 실패입니다. 아이디나 패스워드를 확인해 주세요.");
            req.setAttribute("path", req.getContextPath() + "/auth.do?action=login"); // 로그인 페이지로 보냄.
            req.getRequestDispatcher("/WEB-INF/views/alert.jsp").forward(req, resp);
        }
    }
}
