package com.GRP3.BPA.response;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionResponseTest {
    @Test
    public void testGetSetStatus() {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(true);
        assertTrue(response.isStatus());
        response.setStatus(false);
        assertFalse(response.isStatus());
    }

    @Test
    public void testGetSetMessage() {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage("An error occurred");
        assertEquals("An error occurred", response.getMessage());
        response.setMessage("A different error occurred");
        assertEquals("A different error occurred", response.getMessage());
    }
}