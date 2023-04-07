package com.GRP3.BPA.service;

import com.GRP3.BPA.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

class UserServiceImplTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void testUpdateOTP() {
        User user = new User();
        user.setEmail("john@example.com");
        user.setOtp(null);
        User updatedUser = userService.updateOTP(user);
        assertNotNull(updatedUser.getOtp(), "OTP should not be null");
        assertEquals(6, updatedUser.getOtp().length(), "OTP should be a 6-digit number");
    }

    private String generateOtp() {
        // Generate a random 6-digit OTP
        return String.format("%06d", new Random().nextInt(999999));
    }
}