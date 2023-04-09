package com.GRP3.BPA.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setRole("ROLE_USER");
        user.setIsAuthenticated(true);
    }


    @Test
    void testGetEmail() {
        Assertions.assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetFirstName() {
        Assertions.assertEquals("John", user.getFirstName());
    }

    @Test
    void testGetLastName() {
        Assertions.assertEquals("Doe", user.getLastName());
    }


    @Test
    void testGetPassword() {
        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    void testGetRole() {
        Assertions.assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    void testIsAuthenticated() {
        Assertions.assertTrue(user.getIsAuthenticated());
    }


    @Test
    void testGetUsername() {
        Assertions.assertEquals("test@example.com", user.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        Assertions.assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        Assertions.assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        Assertions.assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        Assertions.assertTrue(user.isEnabled());
    }

}