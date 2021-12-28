package com.gmail.creativegeeksuresh.zyncky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminWebController {


    @GetMapping(value = "/dashboard")
    public ModelAndView dashboardPage(){
        return new ModelAndView("admin/dashboard");
    }
    
    @GetMapping(value = "/profile")
    public ModelAndView profilePage(){
        return new ModelAndView("admin/profile");
    }
}
