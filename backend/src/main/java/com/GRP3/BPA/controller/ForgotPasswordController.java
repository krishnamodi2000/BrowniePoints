//package com.GRP3.BPA.controller;
//
//import com.GRP3.BPA.model.User;
//import com.GRP3.BPA.service.EmailService;
//import com.GRP3.BPA.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//public class ForgotPasswordController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private EmailService emailService;
//
//    @PostMapping("/api/auth/forgot-password")
//    public ResponseEntity<?> resetPassword(@RequestBody User user) {
//        System.out.println(user.getEmail());
//        user = userService.findByEmail(user.getEmail());
//        if (user == null) {
//            return ResponseEntity.badRequest().body("User not found.");
//        }
//
//        user = userService.updatePasswordResetToken(user);
//        String resetLink = "https://example.com/reset-password?token=" + user.getResetToken();
//        emailService.sendResetPasswordEmail(user.getEmail(), user.getResetToken());
//        return ResponseEntity.ok("Reset password link sent to your email.");
//    }
//
//}
