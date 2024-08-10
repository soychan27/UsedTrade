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

    @GetMapping("/AddressForm") //주소 입력 GET
    public String AddressForm() {
        return "AddressForm";
    }

    @PostMapping("/Address")    //주소 입력 POST
    public String Address(Principal principal, Address address, Model model) throws Exception{
        String username = principal.getName();  //principal을 통해 username을 GET
        User user = userService.findByUsername(username);   //GET한 username을 userSerivce를 통해 find하여 user

        address.setUser(user);  //address의 user값을 위 과정을 통해 구한 user로 설정 (로그인한 user 정보)
        addressService.write(address);  //address의 값 저장

        model.addAttribute("message","주소 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/");
        return "message";
    }

    @GetMapping("/AddressView/{id}")    //입력한 주소 확인하기 위한 GET
    public String AddressView(@PathVariable("id") Integer id, Model model, Principal principal) {
        Address address = addressService.addressView(id);
        String username = principal.getName();
        User user = userService.findByUsername(username);

        model.addAttribute("address", address); //address라는 이름으로 "AddressView"로 전달
        model.addAttribute("isAuthor", user.getUserkey().equals(address.getUser().getUserkey()));
        //글쓴이와 현재 로그인한 사용자가 같다면 "isAuthor"로 "AddressView"로 전달
        return "AddressView";
    }

    @PostMapping("/AddressView/delete/{id}")
    public String AddressDelete(@PathVariable("id") Integer id, Model model, Principal principal) {
        //주소에 있는 {id}를 가져오는 @PathVariable
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
            //로그인 안 되어있거나 사용자와 글쓴이가 다를 경우
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
