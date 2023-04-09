package com.GRP3.BPA.service;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.PasswordResetToken.ConfirmOTP;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.utils.Utils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User saveUser(User user) throws RuntimeException;

    Utils matchOtp(ConfirmOTP confirmOTP);

    UserDTO getUser(String username) throws UsernameNotFoundException;

    User login(String email, String password) throws UsernameNotFoundException;

    User emailIsAlreadyExist(User user) throws RuntimeException;

    User findByEmail(String email) throws UsernameNotFoundException;

    User updateOTP(User user);

    Utils changePassword(User user, String newPassword);

    Utils validateResetPassword(User user);
}
