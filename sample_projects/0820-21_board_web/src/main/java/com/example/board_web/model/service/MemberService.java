package com.example.board_web.model.service;

import com.example.board_web.model.repository.MemberRepository;
import com.example.board_web.model.repository.MemberRepositoryMysql;

import java.sql.SQLException;

public class MemberService {
    private static final MemberService instance = new MemberService();
    private final MemberRepository repo = MemberRepositoryMysql.getInstance();

    private MemberService() {
    }

    public static MemberService getInstance() {
        return instance;
    }

    public String login(String userId, String password) throws SQLException {
        return repo.selectOne(userId, password);
    }
}
