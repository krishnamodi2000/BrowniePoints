package com.GRP3.BPA.service;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.PasswordResetToken.ConfirmOTP;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import com.GRP3.BPA.service.ServiceInterface.UserService;
import com.GRP3.BPA.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Random;
import static com.GRP3.BPA.service.EmailValidator.isValid;
import static com.GRP3.BPA.utils.Utils.isValidPassword;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private EmailService emailService;


    private static final int MAX_BOUND=999999;
    /**

     Saves a new user to the database.
     @param user the user to save
     @return the saved user
     @throws RuntimeException if the password is not valid or the email address is already in use
     */
    public User saveUser(User user) throws RuntimeException {
        String password = user.getPassword();
        if(!isValidPassword(password)) throw new RuntimeException("Password should be greater or equal to 8");
        user.setPassword(passwordEncoder.encode(password));
        emailIsAlreadyExist(user);
        User savedUser = userRepository.save(user);
        Student student = new Student();
        student.setBannerId(user.getUserId());
        student.setUser(user);
        studentRepository.save(student);
        return savedUser;
    }

    /**

     Checks if an email address is already in use.
     @param user the user to check
     @return the user if the email address is not in use
     @throws RuntimeException if the email address is already in use
     */
    public User emailIsAlreadyExist(User user) throws RuntimeException{
        User tempsUser = userRepository.findByEmail(user.getEmail());
        if (tempsUser != null && tempsUser.isPresent()) {
            throw new RuntimeException("Email is already exists");
        }
        return userRepository.save(user);
    }

    /**
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
       User user= userRepository.findByEmail(email);
       return user;
    }

    /**

     Generates an OTP for a user and updates the user's OTP in the database.
     @param user the user to generate an OTP for
     @return the updated user
     @throws IllegalArgumentException if the user is null or the email address is invalid
     */
    @Override
    public User updateOTP(User user) {
        if (user == null || !isValid(user.getEmail())) {
            throw new IllegalArgumentException("Invalid user.");
        }
        String otp = generateOtp();
        user.setOtp(otp);
        userRepository.save(user);
        return user;
    }

    /**

     Validates a user's email address and sends them an OTP to reset their password.
     @param user the user to reset the password for
     @return a Utils object containing a success message and a flag indicating success or failure
     */
    @Override
    public Utils validateResetPassword(User user) {
        if(user.getEmail() == null) {
            return new Utils("User not found.", false);
        }
        if (!isValid(user.getEmail())) {
            return new Utils("Invalid email.", false);
        }
        user = findByEmail(user.getEmail());
        user = updateOTP(user);
        emailService.sendOtp(user.getEmail(), user.getOtp());
        return new Utils("OTP sent to " + user.getEmail(), true);
    }

    /**
     Validates an OTP and updates a user's password.
     @param confirmOTP the confirmation object containing the email address, OTP, and new password
     @return a Utils object containing a success message and a flag indicating success or failure
     */
    @Override
    public Utils matchOtp(ConfirmOTP confirmOTP) {
        if (confirmOTP == null) {
            return new Utils("User not found.", false);
        }
        String email = confirmOTP.getEmail();
        String otp = confirmOTP.getOtp();
        String newPassword = confirmOTP.getNewPassword();

        if (email == null || email.isEmpty()) {
            return new Utils("Email cannot be null or empty.", false);
        }
        if (!isValid(email)) {
            return new Utils("Invalid email format.", false);
        }
        if (otp == null || !otp.matches("\\d{6}")) {
            return new Utils("Invalid format, OTP should be 6 digit..", false);
        }
        User currentUser = userRepository.findByEmail(email);
        if(currentUser == null) {
            return new Utils("User not found.", false);
        } else {
            if(currentUser.getOtp().equals(otp)) {
                return changePassword(currentUser, newPassword);
            } else {
                return new Utils("OTP is invalid.", false);
            }
        }
    }


    /**
     Changes a user's password.
     @param user the user to change the password for
     @param newPassword
 */
    @Override
    public Utils changePassword(User user, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            return new Utils("New password cannot be null or empty.", false);
        }
        if (!isValidPassword(newPassword)) {
            return new Utils("New password does not meet requirements.", false);
        }
        user.setPassword(bcryptEncoder.encode(newPassword));
        userRepository.save(user);
        return new Utils("Password changed successfully.", true);
    }

    /**
     Generates a random six-digit OTP code
     @return a string representing the six-digit OTP code
     */
    private static String generateOtp() {
        return String.format("%06d", new Random().nextInt(MAX_BOUND));
    }

    /**
     Gets a user by token and returns a UserDTO object
     @param token the token to retrieve the user
     @return a UserDTO object representing the user retrieved
     @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDTO getUser(String token) throws UsernameNotFoundException{
        User user = this.loadUserByUsername(token);
        if(user == null) throw new UsernameNotFoundException("User does not exist");
        if(user.getRole().equals("ROLE_TEACHER")){
            Teacher teacher = teacherRepository.findByUserId(user.getId());
            UserDTO userDTO = new UserDTO(user.getId(),  teacher.getTeacherId(), user.getEmail(), user.getFirstName(), user.getLastName(),  user.getRole(), user.getToken());
            return userDTO;
        }
        else if(user.getRole().equals("ROLE_STUDENT")){
            Student student = studentRepository.findByUserId(user.getId());
            UserDTO userDTO = new UserDTO(user.getId(),  student.getBannerId(), user.getEmail(), user.getFirstName(), user.getLastName(),  user.getRole(), user.getToken());
            return userDTO;
        }
        return null;
    }


    /**
     Authenticates a user by email and password
     @param email the email of the user to authenticate
     @param password the password of the user to authenticate
     @return a User object representing the authenticated user
     @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public User login(String email, String password) throws UsernameNotFoundException{
        User user = this.loadUserByUsername(email);
        if(user == null) throw new UsernameNotFoundException("User does not exist");
        if(user.getPassword().equals(password)) return user;
        return null;
    }

    /**
     Loads a user by email
     @param username the email of the user to load
     @return a User object representing the user loaded
     @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return user;
    }

    /**
     Updates the user's first name and last name
     @param user the user object containing the new first name and last name
     @param email the email of the user to update
     @return a User object representing the updated user
     @throws UsernameNotFoundException if the user is not found
     */
    public User updateUser(User user, String email){
        User existingUser = userRepository.findByEmail(email);
        if(existingUser == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        userRepository.save(existingUser);
        return existingUser;
    }
}
