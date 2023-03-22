package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public String isUserAssociatedWithTeacher(String userId) {
        Teacher teacher = teacherRepository.findByUserId(userId);
        return teacher.getTeacherId();
    }
}
