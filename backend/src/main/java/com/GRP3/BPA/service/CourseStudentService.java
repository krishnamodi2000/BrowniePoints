package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.GlobalException;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;

import java.util.List;

public interface CourseStudentService {
    CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws GlobalException;
    void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws GlobalException;
    List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws GlobalException;
    void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws GlobalException;

}
