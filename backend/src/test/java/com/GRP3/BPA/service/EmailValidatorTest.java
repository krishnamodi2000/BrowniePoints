package com.GRP3.BPA.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    public void testValidEmail() {
        String validEmail = "john.doe@dal.ca";
        boolean result = EmailValidator.isValid(validEmail);
        assertTrue(result);
    }

    @Test
    public void testInvalidEmail() {
        String invalidEmail = "jane.doe@example.com";
        boolean result = EmailValidator.isValid(invalidEmail);
        assertFalse(result);
    }

    @Test
    public void testEmptyEmail() {
        String emptyEmail = "";
        boolean result = EmailValidator.isValid(emptyEmail);
        assertFalse(result);
    }
}