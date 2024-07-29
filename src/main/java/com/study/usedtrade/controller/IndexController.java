package com.study.usedtrade.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails != null){
            return "loggedInIndex";
        }
        return "Index";
    }
}
