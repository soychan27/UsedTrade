package com.study.usedtrade.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(@AuthenticationPrincipal UserDetails userDetails){
        //현재 인증된 사용자의 정보를 가져오기 위한 @AuthenticationPrincipal, 로그인 되었으면 not null, 안 되어있으면 null
        if(userDetails != null){
            return "loggedInIndex";
        }
        return "Index";
    }
}
