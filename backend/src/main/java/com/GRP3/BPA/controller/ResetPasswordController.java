package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.PasswordResetToken.ConfirmOTP;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.service.EmailService;
import com.GRP3.BPA.service.UserService;
import com.GRP3.BPA.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/api/auth/reset-password")
    public ResponseEntity<?> sendOtp(@RequestBody User user) {
        System.out.println(user.getEmail());
        user = userService.findByEmail(user.getEmail());
        if (user == null) {
            Utils errorResponse =new Utils("User not found.");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
        user = userService.updateOTP(user);
        emailService.sendOtp(user.getEmail(), user.getOtp());
        Utils successResponse = new Utils("OTP sent to " + user.getEmail());
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/api/auth/reset-password-matchotp")
    public ResponseEntity<?> matchOtp(@RequestBody ConfirmOTP confirmOTP) {
        boolean isOtpMatched = false;
        User user = userService.findByEmail(confirmOTP.getEmail());
        if (user != null) {
            isOtpMatched = user.getOtp().equals(confirmOTP.getOtp());
        }
        if (isOtpMatched) {
            Utils successResponse = new Utils("OTP matched successfully.");
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
        else {
            Utils errorResponse =new Utils("Invalid OTP.");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }
}
