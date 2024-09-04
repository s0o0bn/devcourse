package com.example.board_community.model.board;

import java.sql.SQLException;
import java.util.List;

// IBookManager
public interface BoardRepository {
    int insert(BoardDTO board) throws SQLException;

    //    int update(BoardDTO board, String category) throws SQLException;
    int update(BoardDTO board) throws SQLException;

    int updateReadCount(int bno) throws SQLException;

    int delete(int bno) throws SQLException;

    List<BoardDTO> selectAll() throws SQLException;

    BoardDTO selectOne(int id) throws SQLException;
}
