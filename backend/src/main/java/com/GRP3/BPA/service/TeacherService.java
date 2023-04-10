package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * Finds the teacher associated with the given user email.
     *
     * @param email the email of the user
     * @return the teacher ID associated with the user, or null if no teacher is associated with the user
     */
    public String findTeacherAssociatedWithUser(String email) {
        User user = userRepository.findByEmail(email);
        String role= user.getRole();
        String expectedRole="ROLE_TEACHER";
        if(role.equals(expectedRole)){
            int id= user.getId();
            Teacher teacher= teacherRepository.findByUserId(id);
            return teacher.getTeacherId();
        }
        return null; //return some type of exception
    }
}
