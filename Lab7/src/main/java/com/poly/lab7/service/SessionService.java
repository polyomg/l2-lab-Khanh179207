package com.poly.lab7.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    // Lưu giá trị
    public <T> void set(String name, T value) {
        session.setAttribute(name, value);
    }

    // Lấy giá trị
    @SuppressWarnings("unchecked")
    public <T> T get(String name, T defaultValue) {
        T value = (T) session.getAttribute(name);
        return value == null ? defaultValue : value;
    }

    // Xóa giá trị
    public void remove(String name) {
        session.removeAttribute(name);
    }
}
