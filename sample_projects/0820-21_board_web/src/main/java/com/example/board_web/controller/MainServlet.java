package com.example.board_web.controller;

import com.example.board_web.controller.util.MyControllerMapping;
import com.example.board_web.controller.util.MyPageInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

// Spring Web의 DispatcherServlet 역할
@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class MainServlet extends HttpServlet {
    public static final String VIEW_PATH_PREFIX = "/WEB-INF/views";
    public static final String VIEW_PATH_SUFFIX = ".jsp";
    private final MyControllerMapping mapping = new MyControllerMapping();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        System.out.println(url);
        MyController controller = mapping.getController(url);

        HttpSession session = req.getSession();
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null && url.startsWith("/board") && !url.endsWith("/list.do")) {
            System.out.println("1111111111111");
            req.setAttribute("msg", "로그인이 필요합니다.");
            req.setAttribute("path", req.getContextPath() + "/member/loginForm.do");
            req.getRequestDispatcher(VIEW_PATH_PREFIX + "/alert" + VIEW_PATH_SUFFIX).forward(req, resp);
            return;
        }

        Object result = null;
        System.out.println("22222222222222");
        try {
            if (controller == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            System.out.println("3333333333333333");
            result = controller.handleRequest(req, resp);
            if (result == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            if (result instanceof MyPageInfo) {
                // @Controller ; View
                System.out.println("444444444444444444");
                MyPageInfo pageInfo = (MyPageInfo) result;
                System.out.println(pageInfo);
                if (pageInfo.isForward()) {
                    req.getRequestDispatcher(VIEW_PATH_PREFIX + pageInfo.getPath() + VIEW_PATH_SUFFIX).forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + pageInfo.getPath());
                }
            } else {
                // @RestController ; Response Body(JSON)
            }
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher(VIEW_PATH_PREFIX + "/error" + VIEW_PATH_SUFFIX).forward(req, resp);
        }
    }
}
