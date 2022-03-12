package com.gmail.creativegeeksuresh.zyncky.controller;

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

    // @GetMapping(value = "/user/view-books")
    // public ModelAndView viewBooksUserPage() {
    //     ModelAndView mv = new ModelAndView("user/view-books");
    //     mv.addObject("bookList", bookService.getAllBooks());
    //     return mv;
    // }

    @GetMapping(value = "/admin/dashboard")
    public ModelAndView adminDashboardPage() {
        ModelAndView mv = new ModelAndView("admin/dashboard");
        return mv;
    }

    // @GetMapping(value = "/admin/view-users")
    // public ModelAndView viewUsersPage() {
    //     ModelAndView mv = new ModelAndView("admin/view-users");
    //     mv.addObject("userList", userService.getAllUsers());
    //     return mv;
    // }

    @GetMapping(value = "/admin/add-book")
    public ModelAndView addBookPage() {
        ModelAndView mv = new ModelAndView("admin/add-book");

        return mv;
    }

    @GetMapping(value = "/error/page-unavailable")
    public ModelAndView pageUnavailablePage() {
        return new ModelAndView("error/page-not-found");
    }

    @GetMapping(value = "/error/internal-server-error")
    public ModelAndView internalServerErrorPage() {
        return new ModelAndView("error/internal-server-error");
    }

}
