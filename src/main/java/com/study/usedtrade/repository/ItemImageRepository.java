package com.study.usedtrade.repository;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Integer> {
    List<ItemImage> findByItem(Item item);
}
