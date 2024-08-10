package com.study.usedtrade.controller;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.Reply;
import com.study.usedtrade.model.User;
import com.study.usedtrade.service.ReplyService;
import com.study.usedtrade.service.ItemService;
import com.study.usedtrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/Reply/add")
    public String addReply(Reply reply, @RequestParam("itemkey") Integer itemkey,  Principal principal){
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Item item = itemService.itemView(itemkey);
        reply.setUser(user);
        reply.setItem(item);

        replyService.save(reply);

        return "redirect:/itemList/"+ itemkey;
    }

    @PostMapping("/Reply/delete/{id}")
    public String deleteReply(@PathVariable("id") Integer id){
        Reply reply = replyService.findById(id);

        replyService.delete(id);
        return "redirect:/itemList/" + reply.getItem().getItemkey();
    }
}
