package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.AuthenticationRequest;
import com.GRP3.BPA.model.AuthenticationResponse;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.service.AuthenticationService;
import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {


    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder encoder;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private UserService userService;
//    @PostMapping("/api/auth/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) {
//        try {
//            User user = userService.login(email, password);
//            if(user == null) return ResponseEntity.ok(new AuthenticationResponse("not login"));
//        }
//        catch (UsernameNotFoundException e){
//            return ResponseEntity.ok(new AuthenticationResponse("not login"));
//        }
//
//        return ResponseEntity.ok(new AuthenticationResponse("login"));
//    }

//    @PostMapping("/login1")
//    public String login1(@RequestBody User user) {
//
//        return "login";
//    }

    @PostMapping("/api/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
