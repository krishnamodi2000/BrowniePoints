package com.GRP3.BPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtp(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("One-Time Password for Reset Password");
        mailMessage.setText("Your One-Time Password is: " + otp);
        javaMailSender.send(mailMessage);
    }

//    public void sendResetPasswordEmail(String email, String token) {
//        String url = "http://localhost:8080/resetPassword?token=" + token;
//        String subject = "Password reset request";
//        String text = "Please click the following link to reset your password: " + url;
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject(subject);
//        message.setText(text);
//        javaMailSender.send(message);
//    }
}


