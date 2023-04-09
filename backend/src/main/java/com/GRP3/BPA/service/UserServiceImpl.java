package com.GRP3.BPA.service;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.PasswordResetToken.ConfirmOTP;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.repository.TeacherRepository;
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


    public User saveUser(User user) throws RuntimeException {
        String password = user.getPassword();
        if(!isValidPassword(password)) throw new RuntimeException("Password should be greater or equal to 8");
        user.setPassword(passwordEncoder.encode(password));
        emailIsAlreadyExist(user);
        User savedUser = userRepository.save(user);

        //Check if SRP is followed or not in the following code
        Student student = new Student();
        student.setBannerId(user.getUserId());
        student.setUser(user);
        studentRepository.save(student);
        return savedUser;
    }


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


    private static String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

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

    @Override
    public User login(String email, String password) throws UsernameNotFoundException{
        User user = this.loadUserByUsername(email);
        if(user == null) throw new UsernameNotFoundException("User does not exist");
        if(user.getPassword().equals(password)) return user;
        return null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return user;
    }
}
