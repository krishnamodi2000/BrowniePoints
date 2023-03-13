package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.User;
import com.GRP3.BPA.model.UserException;
import com.GRP3.BPA.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController (UserService userService)
    {
        super();
        this.userService = userService;
    }


    @PostMapping(value = "/api/auth/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {

            userService.saveUser(user);

            return new ResponseEntity<>("success", HttpStatus.CREATED);
//            return "success";
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(new UserException(e.getMessage()), HttpStatus.OK);
//            return e.getMessage();
        }
    }


    @PostMapping(value = "/api/user")
    public ResponseEntity<User> getUserDetails(Authentication authentication){
       return new ResponseEntity<User>(userService.getUser(authentication.getName()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping(value = "/test")
    public String test(){
        return "pass";
    }
}
