package com.example.board_community.model.user;

import com.example.board_community.global.exception.UnauthorizedException;
import com.example.board_community.global.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.Optional;

public class AuthService {
    private static final AuthService instance = new AuthService();
    private UserRepository repo = UserRepositoryMysql.getInstance();

    private AuthService() {
    }

    public static AuthService getInstance() {
        return instance;
    }

    public int signup(UserDTO user) throws SQLException {
        return repo.insert(user);
    }

    public boolean login(String userId, String password) throws SQLException  {
        UserDTO user = Optional.ofNullable(repo.selectByUserId(userId))
                .orElseThrow(UserNotFoundException::new);
        if (!user.getPassword().equals(password)) {
            throw new UnauthorizedException();
        }
        return true;
    }
}
