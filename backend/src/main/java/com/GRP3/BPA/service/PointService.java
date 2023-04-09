package com.GRP3.BPA.service;

import com.GRP3.BPA.response.points.StudentPointsAllSubject;
import com.GRP3.BPA.response.points.PointsCreateResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;

import java.util.List;

public interface PointService {
    PointsCreateResponse incrementPoints(String studentId, String courseId);
    CourseStudentsResponse dataOfStudent(String courseId);
    List<StudentPointsAllSubject> pointsAllSubject(String bannerId);
}
