package com.example.board_web.model.repository;

import java.sql.SQLException;

public class MemberRepositoryMysql implements MemberRepository {
    private static final MemberRepositoryMysql instance = new MemberRepositoryMysql();

    private MemberRepositoryMysql() {
    }

    public static MemberRepositoryMysql getInstance() {
        return instance;
    }

    @Override
    public String selectOne(String userId, String password) throws SQLException {
        if (userId.equals("grepp") && password.equals("1234")) return userId;
        return null;
    }
}
