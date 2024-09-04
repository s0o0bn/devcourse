package com.example.board_web.model.repository;

import java.sql.SQLException;

public interface MemberRepository {
    String selectOne(String userId, String password) throws SQLException;
}
