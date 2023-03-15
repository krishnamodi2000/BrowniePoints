package com.GRP3.BPA.service;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) throws RuntimeException {
        String password = user.getPassword();
        if(!Utils.isValidPassword(password)) throw new RuntimeException("Password should be greater or equal to 8");
        user.setPassword(passwordEncoder.encode(password));
        emailIsAlreadyExist(user);
        return userRepository.save(user);
    }


    public User emailIsAlreadyExist(User user) throws RuntimeException{
        User tempsUser = userRepository.findByEmail(user.getEmail());
        if (tempsUser != null && tempsUser.isPresent()) {
            throw new RuntimeException("Email is already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUser(String token) throws UsernameNotFoundException{
        User user = this.loadUserByUsername(token);
        if(user == null) throw new UsernameNotFoundException("User does not exist");
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getContactNumber(), user.getRole(), user.getToken());
        return userDTO;
    }

    @Override
    public User login(String email, String password) throws UsernameNotFoundException{
        User user = this.loadUserByUsername(email);
        if(user == null) throw new UsernameNotFoundException("User does not exist");
        if(user.getPassword().equals(password)) return user;
        return null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return user;
    }
}
