package com.study.usedtrade.controller;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.Reply;
import com.study.usedtrade.model.User;
import com.study.usedtrade.service.ReplyService;
import com.study.usedtrade.service.ItemService;
import com.study.usedtrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/Reply/add")
    public String addReply(@RequestParam("itemkey") Integer itemkey,
                             @RequestParam("content") String content, Principal principal, Model model){
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Item item = itemService.itemView(itemkey);
        Reply reply = new Reply();
        reply.setUser(user);
        reply.setItem(item);
        reply.setContent(content);
        reply.setCreated_at(LocalDateTime.now());

        replyService.save(reply);

        return "redirect:/itemList/"+ itemkey;
    }

    @PostMapping("/Reply/delete/{id}")
    public String deleteReply(@PathVariable("id") Integer id, Principal principal, Model model){
        Reply reply = replyService.findById(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);

        replyService.delete(id);
        return "redirect:/itemList/" + reply.getItem().getItemkey();
    }
}
