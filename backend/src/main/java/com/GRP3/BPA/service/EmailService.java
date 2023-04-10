package com.GRP3.BPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * This method is used to send an OTP to the provided email address.
     *
     * @param email the email address to which the OTP will be sent
     * @param otp the one-time password to be sent in the email
     */
    public void sendOtp(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("One-Time Password for Reset Password");
        mailMessage.setText("Your One-Time Password is: " + otp);
        javaMailSender.send(mailMessage);
    }
}


