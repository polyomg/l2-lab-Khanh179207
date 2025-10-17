package com.poly.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.lab5.service.CookieService;
import com.poly.lab5.service.ParamService;
import com.poly.lab5.service.SessionService;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }

    @PostMapping("/account/login")
    public String login2() {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        // Kiểm tra đăng nhập
        if (un.equals("poly") && pw.equals("123")) {
            sessionService.set("username", un);
            if (rm) {
                // Ghi nhớ tài khoản trong 10 ngày
                cookieService.add("user", un, 10 * 24); // 10 ngày = 240 giờ
            } else {
                // Xóa cookie nếu không chọn remember
                cookieService.remove("user");
            }
        }
        return "/account/login";
    }
}
