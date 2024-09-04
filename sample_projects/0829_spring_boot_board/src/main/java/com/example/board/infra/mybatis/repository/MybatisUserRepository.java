package com.example.board.infra.mybatis.repository;

import com.example.board.infra.mybatis.mapper.UserMapper;
import com.example.board.user.User;
import com.example.board.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {
    private final UserMapper mapper;

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(mapper.selectByUserId(userId));
    }
}
