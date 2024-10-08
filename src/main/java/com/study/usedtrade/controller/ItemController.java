package com.study.usedtrade.controller;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.ItemImage;
import com.study.usedtrade.model.Reply;
import com.study.usedtrade.model.User;
import com.study.usedtrade.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private WishService wishService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ItemImageService itemImageService;

    @GetMapping("/itemList")
    public String itemList(Model model) {
        model.addAttribute("list", itemService.itemList());
        return "ItemList";
    }

    @GetMapping("/itemList/writeForm")
    public String itemListWriteForm(Model model) {
        return "ItemWriteForm";
    }

    @PostMapping("/itemList/write")
    public String itemListWrite(Item item,
                                @RequestParam("files") MultipartFile[] files,
                                Model model,
                                Principal principal) throws Exception {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        System.out.println(user);

        item.setUser(user);
        item.setRdate(LocalDateTime.now());

        itemService.write(item, files);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/itemList");
        return "message";
    }

    @GetMapping("/itemList/modifyForm/{id}")
    public String itemListModifyForm(@PathVariable("id") Integer id, Model model, Principal principal) {
        Item item = itemService.itemView(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);
        if (user == null || !user.getUserkey().equals(item.getUser().getUserkey())) {
            model.addAttribute("message", "수정 권한이 없습니다.");
            model.addAttribute("searchUrl", "/itemList");
            return "message";
        }
        model.addAttribute("item", item);
        return "ItemModifyForm";
    }

    @PostMapping("/itemList/modify/{id}")
    public String itemListModify(@PathVariable("id") Integer id, Item updateItem, Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if (user == null || !user.getUserkey().equals(updateItem.getUser().getUserkey())) {
            model.addAttribute("message", "수정 권한이 없습니다.");
            model.addAttribute("searchUrl", "/itemList");
            return "message";
        }

        Item existingItem = itemService.itemView(id);
        existingItem.setName(updateItem.getName());
        existingItem.setContent(updateItem.getContent());
        existingItem.setPrice(updateItem.getPrice());
        existingItem.setWay(updateItem.getWay());
        existingItem.setComplete(updateItem.getComplete());
        itemService.save(existingItem);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/itemList");
        return "message";
    }

    @GetMapping("/itemList/{id}")
    public String ItemView(@PathVariable("id") Integer id, Model model, Principal principal) {
        Item item = itemService.itemView(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);

        // 이미지 목록을 가져와 모델에 추가
        List<ItemImage> images = itemImageService.findByItem(item);

        model.addAttribute("item", item);
        model.addAttribute("replies", replyService.findAllByItem(item));
        model.addAttribute("isAuthor", user.getUserkey().equals(item.getUser().getUserkey()));
        model.addAttribute("isAdmin", user.getRole().equals("ROLE_ADMIN"));
        model.addAttribute("isInWishList", wishService.isItemInWishList(user, item));
        model.addAttribute("images", images);
        return "ItemView";
    }

    @PostMapping("/itemList/delete/{id}")
    public String ItemDelete(@PathVariable("id") Integer id, Model model, Principal principal) {
        Item item = itemService.itemView(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if ((!user.getUserkey().equals(item.getUser().getUserkey())) && !user.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("message", "삭제 권한이 없습니다.");
            model.addAttribute("searchUrl", "/itemList/{id}");
            return "message";
        }
        itemService.itemDelete(id);
        return "redirect:/itemList";
    }
}