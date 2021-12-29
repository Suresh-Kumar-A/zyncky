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

    @GetMapping(value = "/my-apps")
    public ModelAndView myAppsPage(){
        return new ModelAndView("secure/my-apps");
    }

    @GetMapping(value = "/view-users")
    public ModelAndView viewUsersPage(){
        return new ModelAndView("admin/view-users");
    }
    @GetMapping(value = "/add-user")
    public ModelAndView addUserPage(){
        return new ModelAndView("admin/add-user");
    }
    @GetMapping(value = "/view-apps")
    public ModelAndView viewAppsPage(){
        return new ModelAndView("admin/view-apps");
    }
    @GetMapping(value = "/add-app")
    public ModelAndView addAppPage(){
        return new ModelAndView("admin/add-app");
    }

}
