package com.example.board.user;

import java.util.Optional;

//@Repository
public interface UserRepository {
    Optional<User> findByUserId(String userId);
}
