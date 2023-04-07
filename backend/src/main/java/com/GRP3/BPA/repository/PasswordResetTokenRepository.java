package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
