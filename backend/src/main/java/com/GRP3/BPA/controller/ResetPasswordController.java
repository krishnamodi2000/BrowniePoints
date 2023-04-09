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
        Utils resetPasswordResponse = userService.validateResetPassword(user);
        if(resetPasswordResponse.isStatus()) {
            return new ResponseEntity<>(resetPasswordResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resetPasswordResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/auth/reset-password-matchotp")
    public ResponseEntity<?> matchOtp(@RequestBody ConfirmOTP confirmOTP) {
        Utils response = userService.matchOtp(confirmOTP);
        if(response.isStatus()) {
            return new ResponseEntity<>(userService.matchOtp(confirmOTP), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.matchOtp(confirmOTP), HttpStatus.BAD_REQUEST);
        }
    }
}
