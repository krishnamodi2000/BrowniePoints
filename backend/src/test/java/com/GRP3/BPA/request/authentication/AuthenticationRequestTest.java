package com.GRP3.BPA.request.authentication;


import org.junit.Test;
import static org.junit.Assert.*;

public class AuthenticationRequestTest {

    @Test
    public void testAuthenticationRequest() {
        AuthenticationRequest authRequest = new AuthenticationRequest("test@example.com", "password123");
        assertEquals("test@example.com", authRequest.getEmail());
        assertEquals("password123", authRequest.getPassword());
    }

    @Test
    public void testLombokAnnotations() {
        AuthenticationRequest authRequest = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("password123")
                .build();
        assertNotNull(authRequest);
    }

    @Test
    public void testDefaultConstructor() {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        assertNull(authRequest.getEmail());
        assertNull(authRequest.getPassword());
    }

    @Test
    public void testSetterMethod() {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("password123");
        assertEquals("test@example.com", authRequest.getEmail());
        assertEquals("password123", authRequest.getPassword());
    }
}