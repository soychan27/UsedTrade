package com.study.usedtrade.controller;

import com.study.usedtrade.model.User;
import com.study.usedtrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String LoginForm(){
        System.out.println("로그인 페이지");
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String JoinForm(){
        System.out.println("회원가입 페이지");
        return "joinForm";
    }

    @PostMapping("/join")
    public String Join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }
}
