package com.poly.lab8.service;

import jakarta.mail.MessagingException;
import lombok.Builder;
import lombok.Data;

public interface MailService {
    @Data
    @Builder
    public static class Mail{
        @Builder.Default
        private String from = "Piggaming2007@gmail.com";
        private String to, cc, bcc, subject, body, filenames;
    }

    void send(Mail mail);
    default void send(String to, String subject, String body) throws MessagingException {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    void push(Mail mail);
    default void push(String to, String subject, String body){
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
