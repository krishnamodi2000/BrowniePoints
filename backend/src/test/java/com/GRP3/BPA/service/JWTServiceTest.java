package com.GRP3.BPA.service;

import com.GRP3.BPA.model.User;
import com.GRP3.BPA.service.ServiceInterface.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JWTServiceTest {

    private static final String USERNAME = "user@example.com";
    private static final String ROLE = "ROLE_USER";

    @Mock
    private UserService userService;

    @InjectMocks
    private JwtService jwtService;

//    @Test
//    public void testExtractUsername() {
//        // Arrange
//        String token = jwtService.generateToken();
//
//        // Act
//        String username = jwtService.extractUsername(token);
//
//        // Assert
//        assertEquals(USERNAME, username);
//    }

//    @Test
//    public void testExtractClaim() {
//        // Arrange
//        String token = jwtService.generateToken();
//
//        // Act
//        String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));
//
//        // Assert
//        assertEquals(ROLE, role);
//    }

    @Test
    public void testGenerateToken() {
        // Arrange
        User user = new User();
        user.setEmail(USERNAME);
        user.setRole(ROLE);

        // Act
        String token = jwtService.generateToken(user);

        // Assert
        assertNotNull(token);
    }

    @Test
    public void testIsTokenValid() {
        // Arrange
        User user = new User();
        user.setEmail(USERNAME);
        user.setRole(ROLE);
        String token = jwtService.generateToken(user);

        // Act
        boolean isValid = jwtService.isTokenValid(token, user);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testIsJWTTokenValid() {
        // Arrange
        User user = new User();
        user.setEmail(USERNAME);
        user.setRole(ROLE);
        String token = jwtService.generateToken(user);

        when(userService.findByEmail(USERNAME)).thenReturn(user);

        // Act
        boolean isValid = jwtService.isJWTTokenValid(token);

        // Assert
        assertTrue(isValid);
    }

    // Add more tests here...
}
