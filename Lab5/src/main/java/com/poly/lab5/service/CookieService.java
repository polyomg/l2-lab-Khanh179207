package com.poly.lab5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /** Đọc cookie từ request */
    public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equalsIgnoreCase(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /** Đọc giá trị của cookie */
    public String getValue(String name) {
        Cookie c = get(name);
        return (c != null) ? c.getValue() : "";
    }

    /** Tạo và gửi cookie về client */
    public Cookie add(String name, String value, int hours) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setMaxAge(hours * 3600);
        response.addCookie(c);
        return c;
    }

    /** Xóa cookie khỏi client */
    public void remove(String name) {
        Cookie c = new Cookie(name, "");
        c.setPath("/");
        c.setMaxAge(0);
        response.addCookie(c);
    }
}
