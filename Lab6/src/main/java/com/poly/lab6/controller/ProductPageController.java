package com.poly.lab6.controller;

import com.poly.lab6.dao.ProductDAO;
import com.poly.lab6.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductPageController {

    @Autowired
    private ProductDAO dao;

    // Hiển thị phân trang
    @GetMapping("/page")
    public String paginate(Model model,
                           @RequestParam("p") Optional<Integer> p) {
        // mỗi trang 5 sản phẩm, p = trang hiện tại (bắt đầu từ 0)
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);


        model.addAttribute("page", page);
        return "product/page";
    }
}
