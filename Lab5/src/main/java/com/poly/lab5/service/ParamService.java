package com.poly.lab5.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    /** Đọc chuỗi giá trị của tham số */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    /** Đọc số nguyên giá trị của tham số */
    public int getInt(String name, int defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /** Đọc số thực giá trị của tham số */
    public double getDouble(String name, double defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /** Đọc giá trị boolean của tham số */
    public boolean getBoolean(String name, boolean defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /** Đọc giá trị thời gian của tham số */
    public Date getDate(String name, String pattern) {
        try {
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) return null;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(value);
        } catch (Exception e) {
            throw new RuntimeException("Định dạng ngày không hợp lệ: " + e.getMessage());
        }
    }

    /** Lưu file upload vào thư mục */
    public File save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) return null;

        File dir = new File(request.getServletContext().getRealPath(path));
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}
