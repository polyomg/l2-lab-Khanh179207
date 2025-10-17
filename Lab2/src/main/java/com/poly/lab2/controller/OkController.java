package com.poly.lab2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl")
public class OkController {

    @RequestMapping("/ok")
    public String ok() {
        return "ok"; // trả về ok.html
    }

    @PostMapping("/ok")
    public String m1(Model model) {
        model.addAttribute("message", "Bạn đã nhấn OK 1 (POST)");
        return "ok";
    }

    @GetMapping("/ok")
    public String m2(Model model) {
        model.addAttribute("message", "Bạn đã nhấn OK 2 (GET)");
        return "ok";
    }

    @RequestMapping(value="/ok", params="x")
    public String m3(Model model) {
        model.addAttribute("message", "Bạn đã nhấn OK 3 (param=x)");
        return "ok";
    }
}
