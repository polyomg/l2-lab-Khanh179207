package com.poly.lab7.controller;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Product;
import com.poly.lab7.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ProductController {
    @Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;

    // 🧩 Bài 1: JPQL - Tìm sản phẩm theo khoảng giá
    @RequestMapping("/product/search")
    public String searchJPQL(Model model,
                             @RequestParam("min") Optional<Double> min,
                             @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        System.out.println(">>> Đang dùng JPQL - findByPrice()");
        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }

    // 🧩 Bài 2: JPQL - Tìm kiếm + phân trang
    @RequestMapping("/product/search-and-page")
    public String searchAndPageJPQL(Model model,
                                    @RequestParam("keywords") Optional<String> kw,
                                    @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        System.out.println(">>> Đang dùng JPQL - findByKeywords()");
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);
        return "product/search-and-page";
    }

    // 🧩 Bài 4: DSL - Tìm sản phẩm theo khoảng giá (tự động sinh)
    @RequestMapping("/product/search-dsl")
    public String searchDSL(Model model,
                            @RequestParam("min") Optional<Double> min,
                            @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        System.out.println(">>> Đang dùng DSL - findByPriceBetween()");
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }

    // 🧩 Bài 5: DSL - Tìm kiếm + phân trang theo từ khóa
    @RequestMapping("/product/search-and-page-dsl")
    public String searchAndPageDSL(Model model,
                                   @RequestParam("keywords") Optional<String> kw,
                                   @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        System.out.println(">>> Đang dùng DSL - findAllByNameLike()");
        Page<Product> page = dao.findAllByNameLike("%" + kwords + "%", pageable);

        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);
        return "product/search-and-page";
    }
}
