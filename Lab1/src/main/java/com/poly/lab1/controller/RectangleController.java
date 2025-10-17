package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RectangleController {

    @Autowired
    HttpServletRequest request;

    @GetMapping("/rectangle/form")
    public String form() {
        return "rectangle";
    }

    @PostMapping("/rectangle/calc")
    public String calc(Model model) {
        try {
            double length = Double.parseDouble(request.getParameter("length"));
            double width = Double.parseDouble(request.getParameter("width"));

            double area = length * width;
            double perimeter = 2 * (length + width);

            model.addAttribute("message",
                    "Diện tích: "+ length +"x" +width + "="  + area + " - Chu vi: " + perimeter);
        } catch (Exception e) {
            model.addAttribute("message", "Vui lòng nhập số hợp lệ!");
        }
        return "rectangle";
    }
}
