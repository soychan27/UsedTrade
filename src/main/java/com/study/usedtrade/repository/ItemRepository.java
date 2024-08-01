package com.study.usedtrade.repository;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByUser(User user);
}