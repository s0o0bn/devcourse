package com.example.board_web.controller;

import com.example.board_web.controller.util.MyPageInfo;
import com.example.board_web.model.dto.BoardDTO;
import com.example.board_web.model.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

public class BoardController implements MyController {
    public static final String PREFIX = "/board";
    private final BoardService service = BoardService.getInstance();

    @Override
    public Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String url = req.getServletPath();

        if (url.equals(PREFIX + "/writeForm.do")) {
            return new MyPageInfo("/write_form", true);
        }

        if (url.equals(PREFIX + "/list.do")) {
            List<BoardDTO> boards = service.getBoards();
            req.setAttribute("boards", boards);
            return new MyPageInfo("/list", true);
        }

        if (url.equals(PREFIX + "/read.do")) {
            int no = Integer.parseInt(req.getParameter("no"));
            BoardDTO board = service.getBoard(no);
            req.setAttribute("board", board);
            return new MyPageInfo("/view", true);
        }

        if (url.equals(PREFIX + "/write.do")) {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String writer = (String) req.getSession().getAttribute("loginId");
            BoardDTO dto = new BoardDTO(title, content, writer);
            service.write(dto);
            return new MyPageInfo(PREFIX + "/list.do", false);
        }

        return null;
    }
}
