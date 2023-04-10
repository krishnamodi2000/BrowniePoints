package com.GRP3.BPA.model.PasswordResetToken;

import lombok.Getter;
import lombok.Setter;


/**
 * This class represents the confirm OTP request model used for resetting user password.
 */
public class ConfirmOTP {

    /**
     * The email of the user whose password is being reset.
     */
    @Getter @Setter
    private String email;

    /**
     * The OTP entered by the user.
     */
    @Getter @Setter
    private String otp;

    /**
     * The new password for the user account.
     */
    @Getter @Setter
    private String newPassword;
}
