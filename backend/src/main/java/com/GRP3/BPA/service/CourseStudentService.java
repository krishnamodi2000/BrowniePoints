package com.GRP3.BPA.service;

import com.GRP3.BPA.model.GlobalException;
import com.GRP3.BPA.model.courseStudent.CourseStudent;
import com.GRP3.BPA.model.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.model.courseStudent.CourseStudentRequests;
import com.GRP3.BPA.model.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.model.courseStudent.PointsCreateResponse;

import java.util.List;

public interface CourseStudentService {
    CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws GlobalException;
    void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws GlobalException;
    List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws GlobalException;
    void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws GlobalException;
    PointsCreateResponse incrementPoints(String studentId, String courseId);
    CourseStudentsResponse dataOfStudent(String courseId);
}
