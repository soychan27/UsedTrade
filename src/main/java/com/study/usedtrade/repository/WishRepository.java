package com.study.usedtrade.repository;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import com.study.usedtrade.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Integer> {
    List<Wish> findByUser(User user);
    boolean existsByUserAndItem(User user, Item item);
    Wish findByUserAndItem(User user, Item item);
}
