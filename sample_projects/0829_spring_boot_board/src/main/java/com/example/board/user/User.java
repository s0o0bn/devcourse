package com.example.board.user;

public class User {
    private String userId;
    private String password;

    public boolean isValidPassword(String password) {
        return this.password.equals(password);
    }
}
