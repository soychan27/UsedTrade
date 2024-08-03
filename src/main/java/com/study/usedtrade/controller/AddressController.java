package com.study.usedtrade.controller;

import com.study.usedtrade.model.Address;
import com.study.usedtrade.model.User;
import com.study.usedtrade.service.AddressService;
import com.study.usedtrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/AddressView/{id}")
    public String AddressView(@PathVariable("id") Integer id, Model model, Principal principal) {
        Address address = addressService.addressView(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);

        model.addAttribute("address", address);
        model.addAttribute("isAuthor", user.getUserkey().equals(address.getUser().getUserkey()));
        return "AddressView";
    }

    @PostMapping("/AddressView/delete/{id}")
    public String AddressDelete(@PathVariable("id") Integer id, Model model, Principal principal) {
        Address address = addressService.addressView(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if(!user.getUserkey().equals(address.getUser().getUserkey())){
            model.addAttribute("message", "삭제 권한이 없습니다.");
            model.addAttribute("searchUrl","/myInfo");
            return "message";
        }
        addressService.addressDelete(id);
        return "redirect:/";
    }

    @GetMapping("/AddressView/modifyForm/{id}")
    public String AddressModifyForm(@PathVariable("id") Integer id, Model model, Principal principal){
        Address address = addressService.addressView(id);
        String username= principal.getName();
        User user = userService.findByUsername(username);
        if (user == null || !user.getUserkey().equals(address.getUser().getUserkey())) {
            model.addAttribute("message", "수정 권한이 없습니다.");
            model.addAttribute("searchUrl", "/myInfo");
            return "message";
        }
        model.addAttribute("address",address);
        return "AddressModifyForm";
    }

    @PostMapping("/AddressView/modify/{id}")
    public String AddressModify(@PathVariable("id") Integer id, Address updateAddress, Model model, Principal principal){
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if (user == null || !user.getUserkey().equals(updateAddress.getUser().getUserkey())) {
            model.addAttribute("message", "수정 권한이 없습니다.");
            model.addAttribute("searchUrl", "/itemList");
            return "message";
        }

        Address existingAddress = addressService.addressView(id);
        existingAddress.setName(updateAddress.getName());
        existingAddress.setAddressDetail(updateAddress.getAddressDetail());
        existingAddress.setTel(updateAddress.getTel());
        existingAddress.setReq(updateAddress.getReq());
        addressService.save(existingAddress);

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", String.format("/AddressView/%d", id));
        return "message";
    }
}
