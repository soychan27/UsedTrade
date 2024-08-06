package com.study.usedtrade.service;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import com.study.usedtrade.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageService itemImageService;

    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    public void write(Item item, MultipartFile[] files) throws Exception {
        itemRepository.save(item);

        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path uploadPath = currentPath.resolve("src/main/resources/static/images");
        String uploadDir = uploadPath.toString();

        itemImageService.uploadFile(files, uploadDir, item);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public Item itemView(Integer id) {
        return itemRepository.findById(id).get();
    }

    public void itemDelete(Integer id) {
        itemRepository.deleteById(id);
    }

    public List<Item> itemListUser(User user) {
        return itemRepository.findByUser(user);
    }

    public Item findByItemkey(Integer itemkey) {
        return itemRepository.findByItemkey(itemkey);
    }

    public Optional<Item> getItem(Integer itemkey) {
        return itemRepository.findById(itemkey);
    }
}
