package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.AuthenticationRequest;
import com.GRP3.BPA.model.AuthenticationResponse;
import com.GRP3.BPA.repository.UserRepository;
//import com.GRP3.BPA.service.AuthenticationService;
import com.GRP3.BPA.service.AuthenticationService;
import com.GRP3.BPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request)
    {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
        catch(RuntimeException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
