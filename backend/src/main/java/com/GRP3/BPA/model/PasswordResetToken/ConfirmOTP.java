package com.GRP3.BPA.model.PasswordResetToken;

import lombok.Getter;
import lombok.Setter;

public class ConfirmOTP {

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String otp;

    @Getter @Setter
    private String newPassword;
}
