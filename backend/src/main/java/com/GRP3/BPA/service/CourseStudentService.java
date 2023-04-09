package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;

import java.util.ArrayList;
import java.util.List;

public interface CourseStudentService {

    List<CourseStudent> getStudentsForACourse(String teacherId, String courseId) throws CustomizableException;
    CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws CustomizableException;
    void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws CustomizableException;
    List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws CustomizableException;
    void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws CustomizableException;

}
