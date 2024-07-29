package com.study.usedtrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressController {

    @GetMapping("/address")
    public @ResponseBody String address() {
        return "주소 입력";
    }

}
