package com.GRP3.BPA.controller;

import com.GRP3.BPA.service.EmailValidator;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.exceptions.UserException;
import com.GRP3.BPA.service.ServiceInterface.UserService;
import com.GRP3.BPA.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailValidator emailValidator;

    /**
     * Endpoint for registering a new user.
     * @param user the user object to be registered
     * @return a ResponseEntity with a success message or an error message if the registration failed
     */
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


    /**
     * Endpoint for getting user details.
     * @param authentication the authentication object of the logged-in user
     * @return a ResponseEntity with the user details
     */
    @GetMapping(value = "/api/user")
    public ResponseEntity<?> getUserDetails(Authentication authentication){
       return new ResponseEntity<>(userService.getUser(authentication.getName()), HttpStatus.CREATED);
    }


    /**
     * Endpoint for updating user details.
     * @param email the email of the user to be updated
     * @param user the updated user object
     * @return a ResponseEntity with the updated user object
     */
    @PutMapping(value = "/api/user/{email}")
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user, email), HttpStatus.CREATED);
    }
}
