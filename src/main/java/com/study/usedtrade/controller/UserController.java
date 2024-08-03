package com.study.usedtrade.controller;

import com.study.usedtrade.model.Item;
import com.study.usedtrade.model.User;
import com.study.usedtrade.model.Wish;
import com.study.usedtrade.repository.UserRepository;
import com.study.usedtrade.service.AddressService;
import com.study.usedtrade.service.ItemService;
import com.study.usedtrade.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private WishService wishService;

    @GetMapping("/loginForm")
    public String LoginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String JoinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String Join(User user){
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @GetMapping("/myInfo")
    public String MyInfo(Model model, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        model.addAttribute("addressList", addressService.addressList(user));
        model.addAttribute("itemList", itemService.itemListUser(user));
        List<Wish> wishList = wishService.findByUser(user);
        List<Item> wishItemList = wishList.stream()
                .map(Wish::getItem)
                .collect(Collectors.toList());
        model.addAttribute("wishItemList", wishItemList);
        return "myInfo";
    }
}
