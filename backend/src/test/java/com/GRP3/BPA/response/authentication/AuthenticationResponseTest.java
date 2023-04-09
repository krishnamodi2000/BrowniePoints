package com.GRP3.BPA.response.authentication;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationResponseTest {
    @Test
    public void testGetToken() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("87hjhfgfhj75hgfgfgh");
        String token = response.getToken();
        assertNotNull(token);
        assertFalse(token.startsWith("eyJhbGciOiJIUzI1NiJ9"));
    }

    @Test
    public void testGetTokenWithEmptyUsername() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken("");
        String token = response.getToken();
        assertNotNull(token);
        assertFalse(token.startsWith("eyJhbGciOiJIUzI1NiJ9"));
    }

    @Test
    public void testGetTokenWithNullSecretKey() {
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(null);
        String token = response.getToken();
        assertNull(token);
    }
}