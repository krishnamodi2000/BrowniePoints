package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.request.course.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesForTeacher(String teacherId) throws CustomizableException;
    Course addCourseForTeacher(String teacherId, CourseRequest courseRequest) throws CustomizableException;
    List<Course> addCoursesForTeacher(String teacherId, List<CourseRequest> courseRequests) throws CustomizableException;
    void removeCourseForTeacher(String teacherId, String courseId) throws CustomizableException;

    void removeCoursesForTeacher(String teacherId, List<String> courseIds) throws CustomizableException;

    Course updateCourseForTeacher(String teacherId, CourseRequest courseRequest) throws CustomizableException;

    }
