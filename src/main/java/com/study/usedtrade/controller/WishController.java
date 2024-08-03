package com.study.usedtrade.controller;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import com.study.usedtrade.service.ItemService;
import com.study.usedtrade.service.UserService;
import com.study.usedtrade.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class WishController {

    @Autowired
    private WishService wishService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/wishlist/add")
    public String addWish(@RequestParam("itemkey") Integer itemkey, Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Item item = itemService.findByItemkey(itemkey);

        if (wishService.addWish(user, item)) {
            model.addAttribute("message", "위시리스트에 추가되었습니다.");
        } else {
            model.addAttribute("message", "이미 위시리스트에 존재합니다.");
        }

        model.addAttribute("searchUrl", String.format("/itemList/%d", itemkey));
        return "message";
    }

    @PostMapping("/wishlist/delete")
    public String deleteWish(@RequestParam("itemkey") Integer itemkey, Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Item item = itemService.findByItemkey(itemkey);

        wishService.deleteWish(user,item);

        model.addAttribute("message","위시리스트에서 제거되었습니다.");
        model.addAttribute("searchUrl",String.format("/itemList/%d", itemkey));
        return "message";
    }
}
