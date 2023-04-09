package com.GRP3.BPA.exceptions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserExceptionTest {

    @Test
    public void testCreateUserException() {
        UserException userException = new UserException("Test message");
        assertNotNull(userException);
        assertEquals("Test message", userException.getMessage());
    }

    @Test
    public void testSetMessage() {
        UserException userException = new UserException("Test message");
        userException.setMessage("New message");
        assertEquals("New message", userException.getMessage());
    }

    @Test
    public void testUserExceptionWithEmptyMessage() {
        UserException userException = new UserException("");
        assertEquals("", userException.getMessage());
    }

    @Test
    public void testUserExceptionWithNullMessage() {
        UserException userException = new UserException(null);
        assertNull(userException.getMessage());
    }
}