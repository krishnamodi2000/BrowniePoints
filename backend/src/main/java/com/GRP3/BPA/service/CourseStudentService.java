package com.GRP3.BPA.service;

import com.GRP3.BPA.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface CourseStudentService {
    CourseStudent addStudent(CourseStudentRequest courseStudentRequest);
    void removeStudent(CourseStudentRequest courseStudentRequest);
    void addStudentsFromCsv(File input);

    void removeStudentsFromCsv(File input);
    PointsCreateResponse incrementPoints(String studentId, String courseId);
    CourseStudentsResponse dataOfStudent(String courseId);
    List<StudentPointsAllSubject> pointsAllSubject(String bannerId);
}
