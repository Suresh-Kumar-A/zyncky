package com.gmail.creativegeeksuresh.zyncky.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorPageController {

    @GetMapping(value = { "/", "/page-unavailable" })
    public ModelAndView pageUnavailablePage() {
        return new ModelAndView("error/page-not-found");
    }

    @GetMapping(value = "/service-unavailable")
    public ModelAndView internalServerErrorPage() {
        return new ModelAndView("error/internal-server-error");
    }

}
