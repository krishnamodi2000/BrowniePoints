package com.GRP3.BPA.service;

import com.GRP3.BPA.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User saveUser(User user);


    User getUser(String username) throws UsernameNotFoundException;

    User login(String email, String password) throws UsernameNotFoundException;
}
