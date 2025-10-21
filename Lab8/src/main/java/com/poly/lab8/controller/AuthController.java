package com.poly.lab8.controller;

import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        // Lấy message từ session để hiển thị trên trang login
        Object msg = session.getAttribute("message");
        if (msg != null) {
            model.addAttribute("message", msg);
            session.removeAttribute("message"); // chỉ hiển thị 1 lần
        }
        return "auth/login"; // trỏ tới login.html trong /templates/auth/login.html
    }

    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        Account user = accountService.findById(username);

        if (user == null) {
            model.addAttribute("message", "Invalid username!");
            return "auth/login";
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
            return "auth/login";
        } else {
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");

            // quay lại URI bảo mật trước đó nếu có
            String securityUri = (String) session.getAttribute("securityUri");
            if (securityUri != null) {
                session.removeAttribute("securityUri");
                return "redirect:" + securityUri;
            }
        }

        return "auth/login";
    }
}
