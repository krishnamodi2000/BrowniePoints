package com.GRP3.BPA.service;

import com.GRP3.BPA.model.courseStudent.CourseStudent;
import com.GRP3.BPA.model.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.model.courseStudent.CourseStudentRequests;
import com.GRP3.BPA.model.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.model.courseStudent.PointsCreateResponse;

import java.util.List;

public interface CourseStudentService {
    CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest);
    void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest);
    List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests);
    void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests);
    PointsCreateResponse incrementPoints(String studentId, String courseId);
    CourseStudentsResponse dataOfStudent(String courseId);
}
