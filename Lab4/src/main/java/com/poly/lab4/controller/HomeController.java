package com.poly.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping("/home/index")
    public String index(Model model) {
        return "home/index";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        return "home/about";
    }
}
