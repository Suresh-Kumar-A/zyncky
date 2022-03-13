package com.gmail.creativegeeksuresh.zyncky.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mfa")
public class MfaPageController {

    @GetMapping(value = "/secret-code")
    public ModelAndView secretCodePage(@RequestParam(value = "numOfBox", required = false) Integer count) {
        int numOfBox = 5;
        if (count != null && count.intValue() > 0) {
            numOfBox = count.intValue();
        }
        return new ModelAndView("mfa/secret-code").addObject("numOfBox", numOfBox);
    }
}
