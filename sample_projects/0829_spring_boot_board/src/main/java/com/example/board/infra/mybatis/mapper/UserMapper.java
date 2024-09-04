package com.example.board.infra.mybatis.mapper;

import com.example.board.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByUserId(String userId);
}
