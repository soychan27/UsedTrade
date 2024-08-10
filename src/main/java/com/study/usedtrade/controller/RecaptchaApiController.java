package com.study.usedtrade.controller;

import com.study.usedtrade.service.RecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recaptcha")
@RequiredArgsConstructor
public class RecaptchaApiController {

    private final RecaptchaService recaptchaService;

    @ResponseBody
    @PostMapping("/validate")
    public boolean validRecaptcha(HttpServletRequest request) {
        String recaptcha = request.getParameter("g-recaptcha-response");
        return recaptchaService.verifyRecaptcha(recaptcha);
    }
}
