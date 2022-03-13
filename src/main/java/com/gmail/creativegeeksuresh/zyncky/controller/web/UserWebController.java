package com.gmail.creativegeeksuresh.zyncky.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user")
public class UserWebController {


    @GetMapping(value = "/dashboard")
    public ModelAndView dashboardPage(){
        return new ModelAndView("user/dashboard");
    }
    
    @GetMapping(value = "/profile")
    public ModelAndView profilePage(){
        return new ModelAndView("user/profile");
    }
}
