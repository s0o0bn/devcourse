package com.example.board_community.model.board;

import com.example.board_community.global.exception.BoardNotFoundException;
import com.example.board_community.global.exception.UnauthorizedException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BoardService {
    private static BoardService instance = new BoardService();
    private BoardRepository repo = BoardRepositoryMysql.getInstance();

    private BoardService() {
    }

    public static BoardService getInstance() {
        return instance;
    }

    public BoardDTO read(int bno, Optional<String> userId) throws SQLException {
        BoardDTO board = getBoard(bno);
        if (userId.isEmpty() || !board.getWriter().equals(userId.get())) {
            repo.updateReadCount(bno);
        }
        return board;
    }

    public BoardDTO getBoard(int bno) throws SQLException {
        Optional<BoardDTO> board = Optional.ofNullable(repo.selectOne(bno));
        return board.orElseThrow(BoardNotFoundException::new);
    }

    public int write(BoardDTO board, Optional<String> userId) throws SQLException {
        String writer = userId.orElseThrow(UnauthorizedException::new);
        board.setWriter(writer);
        return repo.insert(board);
    }

    public int update(int bno, BoardDTO board, Optional<String> userId) throws SQLException {
        if (userId.isEmpty()) {
            throw new UnauthorizedException();
        }

        board.setNo(bno);
        return repo.update(board);
    }

    public int delete(int bno) throws SQLException {
        if (repo.selectOne(bno) == null) {
            throw new BoardNotFoundException();
        }

        return repo.delete(bno);
    }

    public List<BoardDTO> readAll() throws SQLException {
        return repo.selectAll();
    }
}
