package com.gmail.creativegeeksuresh.zyncky.controller.web;

import com.gmail.creativegeeksuresh.zyncky.service.internal.UserService;
import com.gmail.creativegeeksuresh.zyncky.service.util.CustomUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GlobalPageController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUtils customUtils;

    @GetMapping(value = { "/", "/global/login" })
    public ModelAndView signInPage() {
        return new ModelAndView("global/sign-in");
    }

    @GetMapping(value = "/global/create-account")
    public ModelAndView createAccountPage() {
        return new ModelAndView("global/create-account");
    }

    @GetMapping(value = "/global/reset-password")
    public ModelAndView resetPasswordPage() {
        return new ModelAndView("global/reset-password");
    }

}
