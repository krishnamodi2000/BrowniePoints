package com.GRP3.BPA.service.ServiceInterface;

import com.GRP3.BPA.response.points.StudentPointsAllSubject;
import com.GRP3.BPA.response.points.PointsCreateResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;

import java.util.List;


public interface PointService {

    /**
     * Increases the points of the specified student for the specified course.
     *
     * @param studentId the ID of the student whose points should be incremented.
     * @param courseId the ID of the course for which the student's points should be incremented.
     * @return a {@code PointsCreateResponse} object containing information about the updated points.
     */
    PointsCreateResponse incrementPoints(String studentId, String courseId);

    /**
     * Retrieves data about all students enrolled in a specified course.
     *
     * @param courseId the ID of the course for which student data should be retrieved.
     * @return a {@code CourseStudentsResponse} object containing data about all students in the course.
     */
    CourseStudentsResponse dataOfStudent(String courseId);

    /**
     * Retrieves the points for all subjects for a specified student.
     *
     * @param bannerId the banner ID of the student for whom points should be retrieved.
     * @return a {@code List} of {@code StudentPointsAllSubject} objects containing data about the student's points.
     */
    List<StudentPointsAllSubject> pointsAllSubject(String bannerId);
}
