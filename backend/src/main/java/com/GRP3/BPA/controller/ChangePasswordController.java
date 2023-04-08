package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.PasswordResetToken.ChangePassword;
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
public class ChangePasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/api/auth/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
        try {
            User response = userService.changePassword(changePassword.getEmail(), changePassword.getNewPassword());
            Utils successResponse = new Utils("Password changed successfully.", true);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Utils errorResponse = new Utils(e.getMessage(), false);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
//    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
//            User user = userService.changePassword(changePassword.getEmail(), changePassword.getNewPassword());
//            if(user != null){
//            Utils successResponse = new Utils("Password updated successfully.", true);
//            return new ResponseEntity<>(successResponse, HttpStatus.OK);
//        } else {
//            Utils errorResponse =new Utils("something went wrong", false);
//            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
//        }
//    }
}
