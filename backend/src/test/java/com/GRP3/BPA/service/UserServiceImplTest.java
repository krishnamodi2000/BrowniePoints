package com.GRP3.BPA.service;

import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Test
    public void testValidateResetPassword_ValidUser() {
        User user = new User();
        user.setEmail("john@dal.ca");
        Mockito.when(userRepository.findByEmail("john@dal.ca")).thenReturn(user);
        Utils result = userService.validateResetPassword(user);
        assertTrue(result.isStatus());
        assertEquals("OTP sent to john@dal.ca",result.getMessage());
    }

    @Test
    public void testValidateResetPassword_NullEmail() {
        User user = new User();
        Utils result = userService.validateResetPassword(user);
        assertFalse(result.isStatus());
        assertEquals("User not found.", result.getMessage());
    }

    @Test
    public void testValidateResetPassword_InvalidEmail() {
        User user = new User();
        user.setEmail("john@gmail.com");
        Utils result = userService.validateResetPassword(user);
        assertFalse(result.isStatus());
        assertEquals("Invalid email.", result.getMessage());
    }
}





