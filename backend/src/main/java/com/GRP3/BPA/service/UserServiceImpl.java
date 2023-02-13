package com.GRP3.BPA.service;

import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository){
//        super();
//        this.userRepository = userRepository;
//    }

    public User saveUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
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
