package com.poly.lab8.controller;

import com.poly.lab8.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Autowired
    MailService mailService;

    //Bài 1
    @ResponseBody
    @RequestMapping("/mail/send")
    public String send() {
        try {
            String emailTo = "Khanhnts00673@fpt.edu.vn";
            String body = "Xin chào " + emailTo
                    + "<br>Chúng tôi đã nhận được yêu cầu hỗ trợ của bạn và sẽ phản hồi trong thời gian sớm nhất, thường là trong vòng 24 giờ làm việc."
                    +  "<br>Cảm ơn bạn đã kiên nhẫn."
                    + "<br>Trân trọng, Đội ngũ hỗ trợ khách hàng Cửa hàng ABC";

            mailService.send( emailTo, "Cảm ơn bạn đã liên hệ với chúng tôi",body);
            return "Mail của bạn đã được gửi đi";
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }

    //Bài 2
    @ResponseBody
    @RequestMapping("/mail/sendQueue")
    public String send(Model model) {
        String emailTo = "Khanhnts00673@fpt.edu.vn";
        String body = "Xin chào " + emailTo
                + "<br>Chúng tôi đã đợi bạn từ chiều";

        mailService.push(emailTo, "Subject", body);
        return "Mail của bạn đã được xếp vào hàng đợi";
    }

    //Bài 3
    @RequestMapping("/mail/form")
    public String form() {
        return "index";
    }

    @PostMapping("/mail/form/send")
    public String formSend(@RequestParam String to,
                           @RequestParam String  subject,
                           @RequestParam String  body,
                           @RequestParam("action") String actionType) {
        MailService.Mail mailObject = MailService.Mail.builder().to(to).subject(subject).body(body).build();
        if("sendNow".equals(actionType)) {
            mailService.send(mailObject);
        }else{
            mailService.push(mailObject);
        }
        return "index";
    }

}
