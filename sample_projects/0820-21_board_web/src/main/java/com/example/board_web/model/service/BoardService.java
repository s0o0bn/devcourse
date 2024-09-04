package com.example.board_web.model.service;

import com.example.board_web.model.dto.BoardDTO;
import com.example.board_web.model.repository.BoardRepository;
import com.example.board_web.model.repository.BoardRepositoryMysql;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
    private static final BoardService instance = new BoardService();
    private final BoardRepository repo = BoardRepositoryMysql.getInstance();

    private BoardService() {
    }

    public static BoardService getInstance() {
        return instance;
    }

    public List<BoardDTO> getBoards() throws SQLException {
        return repo.selectAll();
    }

    public BoardDTO getBoard(int no) throws SQLException {
        return repo.selectOne(no);
    }

    public int write(BoardDTO dto) throws SQLException {
        return repo.insert(dto);
    }
}
