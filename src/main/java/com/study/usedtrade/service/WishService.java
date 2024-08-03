package com.study.usedtrade.service;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import com.study.usedtrade.model.Wish;
import com.study.usedtrade.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    public List<Wish> getWishlistByUser(User user){
        return wishRepository.findByUser(user);
    }

    public boolean addWish(User user, Item item){
        if (wishRepository.existsByUserAndItem(user, item)){
            return false;
        }
        Wish wish = new Wish();
        wish.setUser(user);
        wish.setItem(item);
        wishRepository.save(wish);
        return true;
    }

    public void deleteWish(User user, Item item) {
        Wish wish = wishRepository.findByUserAndItem(user, item);
        if(wish != null) {
            wishRepository.delete(wish);
        }
    }

    public List<Wish> findByUser(User user) {
        return wishRepository.findByUser(user);
    }

    public boolean isItemInWishList(User user, Item item) {
        return wishRepository.findByUserAndItem(user, item) != null;
    }
}
