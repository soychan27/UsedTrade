package com.study.usedtrade.service;

import com.study.usedtrade.model.Address;
import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import com.study.usedtrade.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/itemList")
    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    public void write(Item item) throws Exception{
        itemRepository.save(item);
    }


    public void save(Item item){
        itemRepository.save(item);
    }

    public Item itemView(Integer id){
        return itemRepository.findById(id).get();
    }

    public void itemDelete(Integer id){
        itemRepository.deleteById(id);
    }

    public List<Item> itemListUser(User user) {
        return itemRepository.findByUser(user);
    }

    public Item findByItemkey(Integer itemkey) {
        return itemRepository.findByItemkey(itemkey);
    }
}