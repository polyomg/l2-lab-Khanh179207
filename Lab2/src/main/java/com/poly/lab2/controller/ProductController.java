package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {


    private Product defaultProduct = new Product("iPhone 30", 5000.0);

    private List<Product> items = new ArrayList<>();

    public ProductController() {
        items.add(new Product("A", 1.0));
        items.add(new Product("B", 12.0));
    }

    @GetMapping("/product/form")
    public String form(Model model) {
        model.addAttribute("p1", defaultProduct);
        model.addAttribute("p2", new Product(defaultProduct.getName(), defaultProduct.getPrice()));

        model.addAttribute("items", items);

        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("p2") Product p, Model model) {
        model.addAttribute("p1", defaultProduct);
        model.addAttribute("p2", p);
        items.add(new Product(p.getName(), p.getPrice()));
        model.addAttribute("items", items);

        return "product/form";
    }
}
