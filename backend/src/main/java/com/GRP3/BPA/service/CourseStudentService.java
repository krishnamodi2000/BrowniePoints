package com.GRP3.BPA.service;

import com.GRP3.BPA.model.CourseStudent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

public interface CourseStudentService {
    CourseStudent addStudent(String courseId, String teacherId, String studentId);
    void removeStudent(String courseId, String teacherId, String studentId);
    void addStudentsFromCsv(String courseId, String teacherId, InputStream csv);
}
