package com.GRP3.BPA.service;

import com.GRP3.BPA.DTO.UserDTO;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.model.student.Student;
import com.GRP3.BPA.model.teacher.Teacher;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.repository.student.StudentRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import com.GRP3.BPA.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Random;



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


    public User saveUser(User user) throws RuntimeException {
        String password = user.getPassword();
        if(!Utils.isValidPassword(password)) throw new RuntimeException("Password should be greater or equal to 8");
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
        String otp = generateOtp();
        user.setOtp(otp);
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean matchOtp(String email, String otp) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        if (user.getOtp().equals(otp)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public User changePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(bcryptEncoder.encode(newPassword));
            return userRepository.save(user);
        }
        return null;
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
