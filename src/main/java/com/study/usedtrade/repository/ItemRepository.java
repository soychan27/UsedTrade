package com.study.usedtrade.repository;

import com.study.usedtrade.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Page<Item> findByNameContaining(String searchKeyword, Pageable pageable);
}
