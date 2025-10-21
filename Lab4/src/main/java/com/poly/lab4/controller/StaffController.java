//bai 1 va 2
package com.poly.lab4.controller;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.poly.lab4.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/create/form")
    public String createForm(Model model) {
        model.addAttribute("staff", new Staff());
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "/demo/staff-create";
    }

    @PostMapping("/create/save")
    public String createSave(Model model,
                             @RequestPart("photo_file") MultipartFile photoFile,
                             @Valid @ModelAttribute("staff") Staff staff,
                             Errors errors) {

        // Nếu có file thì set lại cho staff (để hiện ảnh sau submit)
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            // Hiển thị "Xin chào <tên>" thay cho thông báo chung
            model.addAttribute("message", "Xin chào " + staff.getFullname());
        }

        // Giữ lại staff để view render không bị reset dữ liệu
        model.addAttribute("staff", staff);

        return "/demo/staff-validate";
    }
}
