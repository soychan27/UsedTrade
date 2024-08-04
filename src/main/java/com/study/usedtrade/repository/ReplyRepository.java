package com.study.usedtrade.repository;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findAllByItem(Item item);
}