package com.GRP3.BPA.repository.course;

import com.GRP3.BPA.model.Course;

import java.util.List;

public interface CourseCustomRepository {
    Course findById(String courseId);
    List<Course> findAllById(List<String> courseIds);
}
