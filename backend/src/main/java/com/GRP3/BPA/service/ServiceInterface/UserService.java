package com.GRP3.BPA.service.ServiceInterface;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.PasswordResetToken.ConfirmOTP;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.utils.Utils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    /**
     * Saves a new user to the system.
     * @param user The user object to save.
     * @return The saved user object.
     * @throws RuntimeException If the user already exists in the system.
     */
    User saveUser(User user) throws RuntimeException;

    /**
     * Matches the provided OTP to the OTP associated with the user in the system.
     * @param confirmOTP The ConfirmOTP object containing the OTP to match.
     * @return A Utils object containing the result of the operation.
     */
    Utils matchOtp(ConfirmOTP confirmOTP);

    /**
     * Retrieves the user with the specified username.
     * @param username The username of the user to retrieve.
     * @return A UserDTO object containing the user's data.
     * @throws UsernameNotFoundException If a user with the specified username does not exist.
     */
    UserDTO getUser(String username) throws UsernameNotFoundException;

    /**
     * Authenticates a user with the specified email and password.
     * @param email The email of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return The authenticated user object.
     * @throws UsernameNotFoundException If a user with the specified email does not exist.
     */
    User login(String email, String password) throws UsernameNotFoundException;

    /**
     * Checks if a user with the specified email already exists in the system.
     * @param user The user object to check.
     * @return The existing user object if a user with the specified email already exists, null otherwise.
     * @throws RuntimeException If an error occurs while checking for an existing user.
     */
    User emailIsAlreadyExist(User user) throws RuntimeException;


    /**
     * Retrieves the user with the specified email.
     * @param email The email of the user to retrieve.
     * @return The user object with the specified email.
     * @throws UsernameNotFoundException If a user with the specified email does not exist.
     */
    User findByEmail(String email) throws UsernameNotFoundException;

    /**
     * Updates the specified user's information in the system.
     * @param user The user object with the updated information.
     * @param email The email of the user to update.
     * @return The updated user object.
     */
    User updateUser(User user, String email);

    /**
     * Updates the OTP associated with the specified user in the system.
     * @param user The user object with the updated OTP.
     * @return The updated user object.
     */
    User updateOTP(User user);


    /**
     * Changes the password of the specified user in the system.
     * @param user The user object to update.
     * @param newPassword The new password to set for the user.
     * @return A Utils object containing the result of the operation.
     */
    Utils changePassword(User user, String newPassword);

    /**
     * Validates a password reset request for the specified user in the system.
     * @param user The user object to validate the password reset request for.
     * @return A Utils object containing the result of the operation.
     */
    Utils validateResetPassword(User user);
}
