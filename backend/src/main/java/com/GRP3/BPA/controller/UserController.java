package com.GRP3.BPA.controller;

import com.GRP3.BPA.service.EmailValidator;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.exceptions.UserException;
import com.GRP3.BPA.service.UserService;
import com.GRP3.BPA.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailValidator emailValidator;

    @PostMapping(value = "/api/auth/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            if (!emailValidator.isValid(user.getEmail())) {
                throw new IllegalArgumentException("Invalid email address.");
            }
            userService.saveUser(user);
            Utils successResponse = new Utils("User successfully registered.", true);
            return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(new UserException(e.getMessage()), HttpStatus.OK);
        }
    }


    @GetMapping(value = "/api/user")
    public ResponseEntity<?> getUserDetails(Authentication authentication){
       return new ResponseEntity<>(userService.getUser(authentication.getName()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping(value = "/test")
    public String test(){
        return "pass";
    }
}
