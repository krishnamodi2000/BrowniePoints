package com.GRP3.BPA.service;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User saveUser(User user) throws RuntimeException;

    UserDTO getUser(String username) throws UsernameNotFoundException;

    User login(String email, String password) throws UsernameNotFoundException;

    User emailIsAlreadyExist(User user) throws RuntimeException;
}
