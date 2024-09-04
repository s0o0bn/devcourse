package com.example.board_community.controller;

import com.example.board_community.global.exception.BoardNotFoundException;
import com.example.board_community.global.exception.UnauthorizedException;
import com.example.board_community.global.util.SessionUtil;
import com.example.board_community.model.board.BoardDTO;
import com.example.board_community.model.board.BoardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/board.do")
public class BoardServlet extends HttpServlet {
    private final BoardService service = BoardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Optional<String> userId = SessionUtil.getUserIdFromSession(req);

        try {
            if (action.equals("list")) {
                List<BoardDTO> boards = service.readAll();

                req.setAttribute("boards", boards);
                req.setAttribute("userId", userId);
                req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
            } else if (action.equals("new")) {
                if (userId.isEmpty()) {
                    throw new UnauthorizedException();
                }
                req.getRequestDispatcher("/WEB-INF/views/writeForm.jsp").forward(req, resp);
            } else if (action.equals("detail")) {
                int bno = Integer.parseInt(req.getParameter("no"));
                BoardDTO board = service.read(bno, userId);

                req.setAttribute("board", board);
                req.setAttribute("userId", userId);
                req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req, resp);
            } else if (action.equals("update")) {
                if (userId.isEmpty()) {
                    throw new UnauthorizedException();
                }

                int bno = Integer.parseInt(req.getParameter("no"));
                BoardDTO board = service.getBoard(bno);

                req.setAttribute("board", board);
                req.getRequestDispatcher("/WEB-INF/views/updateForm.jsp").forward(req, resp);
            } else if (action.equals("delete")) {
                if (userId.isEmpty()) {
                    throw new UnauthorizedException();
                }

                int bno = Integer.parseInt(req.getParameter("no"));
                int result = service.delete(bno);

                if (result == 1) {
                    resp.sendRedirect(req.getContextPath() + "/alert.do?action=delete");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
        } catch (BoardNotFoundException e) {
            resp.sendRedirect(req.getContextPath() + "/alert.do?action=not-found");
        } catch (UnauthorizedException e) {
            resp.sendRedirect(req.getContextPath() + "/alert.do?action=login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        BoardDTO board = new BoardDTO(title, content);

        Optional<String> userId = SessionUtil.getUserIdFromSession(req);
        String action = req.getParameter("action");
        try {
            int result = 0;
            if (action.equals("new")) {
                result = service.write(board, userId);
            } else if (action.equals("update")) {
                int bno = Integer.parseInt(req.getParameter("no"));
                result = service.update(bno, board, userId);
            }

            if (result == 1) {
                resp.sendRedirect(req.getContextPath() + "/alert.do?action=write");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        } catch (UnauthorizedException e) {
            resp.sendRedirect(req.getContextPath() + "/alert.do?action=login");
        }
    }
}
