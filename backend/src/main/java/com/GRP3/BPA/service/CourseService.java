package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.GlobalException;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.request.course.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesForTeacher(String teacherId) throws GlobalException;
    Course addCourseForTeacher(String teacherId, CourseRequest courseRequest) throws GlobalException;
    List<Course> addCoursesForTeacher(String teacherId, List<CourseRequest> courseRequests) throws GlobalException;
    void removeCourseForTeacher(String teacherId, String courseId) throws GlobalException;

    void removeCoursesForTeacher(String teacherId, List<String> courseIds) throws GlobalException;

    }
