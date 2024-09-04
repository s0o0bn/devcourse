package com.example.board_community.model.user;

public class UserDTO {
    private int no;
    private String name;
    private String userId;
    private String password;

    public UserDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UserDTO(int no, String name, String userId, String password) {
        this.no = no;
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    public UserDTO(String name, String userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
