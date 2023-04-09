package com.GRP3.BPA.exceptions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomizableExceptionTest {

    @Test
    public void testCreateCustomizableException() {
        CustomizableException customizableException = CustomizableException.builder()
                .status(false)
                .message("Test message")
                .build();

        assertNotNull(customizableException);
        assertFalse(customizableException.isStatus());
        assertEquals("Test message", customizableException.getMessage());
    }

    @Test
    public void testGettersAndSetters() {
        CustomizableException customizableException = new CustomizableException();

        customizableException.setStatus(true);
        assertTrue(customizableException.isStatus());

        customizableException.setMessage("New message");
        assertEquals("New message", customizableException.getMessage());
    }

    @Test
    public void testCustomizableExceptionWithTrueStatus() {
        CustomizableException customizableException = CustomizableException.builder()
                .status(true)
                .message("Test message")
                .build();

        assertTrue(customizableException.isStatus());
        assertEquals("Test message", customizableException.getMessage());
    }

    @Test
    public void testCustomizableExceptionWithFalseStatus() {
        CustomizableException customizableException = CustomizableException.builder()
                .status(false)
                .message("Test message")
                .build();

        assertFalse(customizableException.isStatus());
        assertEquals("Test message", customizableException.getMessage());
    }
}