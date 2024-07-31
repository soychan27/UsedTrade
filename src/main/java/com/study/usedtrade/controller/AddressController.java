package com.study.usedtrade.controller;

import com.study.usedtrade.model.Address;
import com.study.usedtrade.model.User;
import com.study.usedtrade.service.AddressService;
import com.study.usedtrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @GetMapping("/AddressForm")
    public String AddressForm() {
        return "AddressForm";
    }

    @PostMapping("/Address")
    public String Address(Principal principal, Address address, Model model) throws Exception{
        String username = principal.getName();
        User user = userService.findByUsername(username);

        address.setUser(user);
        addressService.write(address);

        model.addAttribute("message","주소 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/");
        return "message";
    }

}
