package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for managing {@link User} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address to search for
     * @return the user with the specified email address, or null if no such user exists
     */
    User findByEmail(String email);
}
