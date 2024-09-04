package com.example.board_community.model.user;

import java.sql.SQLException;

public interface UserRepository {
    int insert(UserDTO user) throws SQLException;

    UserDTO selectByUserId(String userId) throws SQLException;

    boolean existsByUserId(String userId) throws SQLException;
}
