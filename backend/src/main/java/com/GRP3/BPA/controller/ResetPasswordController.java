package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.PasswordResetToken.ConfirmOTP;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.service.EmailService;
import com.GRP3.BPA.service.ServiceInterface.UserService;
import com.GRP3.BPA.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class represents the REST endpoints for reset password feature.
 */
@RestController
public class ResetPasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    /**
     * This endpoint is used to send an OTP to the user's registered email address for resetting the password.
     * @param user The user object containing the email address to which the OTP is to be sent.
     * @return Returns a ResponseEntity with a status of OK if the OTP is sent successfully or BAD_REQUEST otherwise.
     */
    @PostMapping("/api/auth/reset-password")
    public ResponseEntity<?> sendOtp(@RequestBody User user) {
        Utils resetPasswordResponse = userService.validateResetPassword(user);
        if(resetPasswordResponse.isStatus()) {
            return new ResponseEntity<>(resetPasswordResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resetPasswordResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This endpoint is used to validate the OTP entered by the user and reset the password if the OTP is correct.
     * @param confirmOTP The ConfirmOTP object containing the user's email address, OTP, and new password.
     * @return Returns a ResponseEntity with a status of OK if the password is reset successfully or BAD_REQUEST otherwise.
     */
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
