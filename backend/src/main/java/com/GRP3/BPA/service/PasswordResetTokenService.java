package com.GRP3.BPA.service;

import com.GRP3.BPA.model.PasswordResetToken;
import com.GRP3.BPA.model.User;

public interface PasswordResetTokenService {
    PasswordResetToken createPasswordResetToken(User user);

    PasswordResetToken getPasswordResetToken(String token);

    void deletePasswordResetToken(PasswordResetToken token);
}
