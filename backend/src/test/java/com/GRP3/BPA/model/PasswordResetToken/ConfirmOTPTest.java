package com.GRP3.BPA.model.PasswordResetToken;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConfirmOTPTest {

    @Test
    public void testSetEmail() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        String email = "test@example.com";
        confirmOTP.setEmail(email);
        Assertions.assertEquals(email, confirmOTP.getEmail());
    }

    @Test
    public void testSetOTP() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        String otp = "123456";
        confirmOTP.setOtp(otp);
        Assertions.assertEquals(otp, confirmOTP.getOtp());
    }

    @Test
    public void testSetNewPassword() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        String newPassword = "newPassword123";
        confirmOTP.setNewPassword(newPassword);
        Assertions.assertEquals(newPassword, confirmOTP.getNewPassword());
    }

    @Test
    public void testSetEmailNull() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        try {
            confirmOTP.setEmail(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetOTPTNull() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        try {
            confirmOTP.setOtp(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetNewPasswordNull() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        try {
            confirmOTP.setNewPassword(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetEmailEmpty() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        try {
            confirmOTP.setEmail("");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetOTPEmpty() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        try {
            confirmOTP.setOtp("");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetNewPasswordEmpty() {
        ConfirmOTP confirmOTP = new ConfirmOTP();
        try {
            confirmOTP.setNewPassword("");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
