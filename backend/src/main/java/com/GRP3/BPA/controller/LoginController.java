package com.GRP3.BPA.controller;

import com.GRP3.BPA.request.authentication.AuthenticationRequest;
import com.GRP3.BPA.response.authentication.AuthenticationResponse;
import com.GRP3.BPA.exceptions.UserException;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.service.AuthenticationService;
import com.GRP3.BPA.service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     This method returns the status of the application.
     @return a string indicating that the application is running
     */
    @GetMapping("/status")
    public String getStatus() {
        return "Application is running.";
    }

    /**
     This method authenticates the user based on the provided AuthenticationRequest.
     @param request an AuthenticationRequest object containing the user's login credentials
     @return a ResponseEntity containing the AuthenticationResponse object if authentication is successful
     @throws UserException if authentication fails
     */
    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new UserException(e.getMessage()), HttpStatus.OK);
        }
    }
}
