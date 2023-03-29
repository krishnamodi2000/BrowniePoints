package com.GRP3.BPA.service;

import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.CourseStudentRequest;
import com.GRP3.BPA.model.CourseStudentRequests;
import com.GRP3.BPA.model.CourseStudentsResponse;
import com.GRP3.BPA.model.PointsCreateResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface CourseStudentService {
    CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest);
    void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest);
    List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests);
    void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests);
    PointsCreateResponse incrementPoints(String studentId, String courseId);
    CourseStudentsResponse dataOfStudent(String courseId);
}
