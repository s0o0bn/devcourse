package com.example.board_community.model.user;

import com.example.board_community.global.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryMysql implements UserRepository {
    private static final UserRepositoryMysql instance = new UserRepositoryMysql();
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private UserRepositoryMysql() {
    }

    public static UserRepositoryMysql getInstance() {
        return instance;
    }

    @Override
    public int insert(UserDTO user) throws SQLException {
        int result;
        try {
            String sql = " INSERT INTO " +
                    " USER_TB(NAME, USER_ID, PASSWORD) VALUES(?,?,?) ";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUserId());
            ps.setString(3, user.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("insert error");
            throw ex;
        } finally {
            DBUtil.close(ps, conn);
        }
        return result;
    }

    @Override
    public UserDTO selectByUserId(String userId) throws SQLException {
        UserDTO user = null;

        try {
            String sql = "SELECT UNO, NAME, USER_ID, PASSWORD FROM USER_TB WHERE USER_ID = ?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = makeUserDTO(rs);
            }
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }

    @Override
    public boolean existsByUserId(String userId) throws SQLException {
        try {
            String sql = "SELECT EXISTS(SELECT UNO FROM USER_TB WHERE USER_ID = ?) AS 'RESULT'";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("result") == 1;
        } catch (SQLException ex) {
            System.out.println("select error");
            throw ex;
        } finally {
            DBUtil.close(rs, ps, conn);
        }
    }

    private UserDTO makeUserDTO(ResultSet rs) throws SQLException {
        int uno = rs.getInt("UNO");
        String name = rs.getString("NAME");
        String userId = rs.getString("USER_ID");
        String password = rs.getString("PASSWORD");
        return new UserDTO(uno, name, userId, password);
    }
}
