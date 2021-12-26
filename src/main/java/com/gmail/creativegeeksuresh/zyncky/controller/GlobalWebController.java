package com.gmail.creativegeeksuresh.zyncky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/global")
public class GlobalWebController {

    @GetMapping(value = "/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }
    
    @GetMapping(value = "/create-account")
    public ModelAndView createAccountPage(){
        return new ModelAndView("global/create-account");
    }
}