package com.study.usedtrade.repository;

import com.study.usedtrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
