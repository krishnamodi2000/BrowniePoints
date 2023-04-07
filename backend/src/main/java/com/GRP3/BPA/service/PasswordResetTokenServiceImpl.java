package com.GRP3.BPA.service;

import com.GRP3.BPA.model.PasswordResetToken.PasswordResetToken;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken createPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
//        passwordResetToken.setToken(token);
//        passwordResetToken.setUser(user);
//        passwordResetToken.setExpiryDate(getExpiryDate());
//        return passwordResetTokenRepository.save(passwordResetToken);
        return passwordResetToken;
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
//        return passwordResetTokenRepository.findByToken(token);
        return new PasswordResetToken();
    }

    @Override
    public void deletePasswordResetToken(PasswordResetToken token) {
//        passwordResetTokenRepository.delete(token);
        return;
    }

    private Date getExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 24);
        return calendar.getTime();
    }
}



