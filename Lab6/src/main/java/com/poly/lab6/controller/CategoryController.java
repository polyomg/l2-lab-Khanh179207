package com.poly.lab6.controller;

import com.poly.lab6.dao.CategoryDAO;
import com.poly.lab6.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO dao;

    // Trang chính
    @RequestMapping("/category/index")
    public String index(Model model) {
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    // Chỉnh sửa
    @RequestMapping("/category/edit/{id}")
        public String edit(Model model, @PathVariable("id") String id) {
            Category item = dao.findById(id).orElse(new Category());
            model.addAttribute("item", item);
            model.addAttribute("items", dao.findAll());
        return "category/index";
    }

    // Tạo mới
    @RequestMapping("/category/create")
    public String create(Category item) {
        dao.save(item);
        return "redirect:/category/index";
    }

    // Cập nhật
    @RequestMapping("/category/update")
    public String update(Category item) {
        dao.save(item);
        return "redirect:/category/edit/" + item.getId();
    }

    // Xóa
    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        dao.deleteById(id);
        return "redirect:/category/index";
    }
}
