package com.GRP3.BPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendResetPasswordEmail(String email, String token) {
        String url = "http://localhost:8080/resetPassword?token=" + token;
        String subject = "Password reset request";
        String text = "Please click the following link to reset your password: " + url;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}


