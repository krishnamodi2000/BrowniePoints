package com.GRP3.BPA.controller;

import com.GRP3.BPA.response.points.StudentPointsAllSubject;
import com.GRP3.BPA.response.points.PointsCreateResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.service.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers/points")
public class PointController {
    @Autowired
    private PointServiceImpl pointService;

    /**
     * This method increments points for a student in a particular course
     *
     * @param studentId The ID of the student to increment points for
     * @param courseId The ID of the course to increment points for
     * @return A ResponseEntity containing the PointsCreateResponse object and an HTTP status code of 201 CREATED on success, or an error message and an HTTP status code of 400 BAD_REQUEST on failure
     */
    @PutMapping("/{courseId}/{studentId}")
    public ResponseEntity<Object> incrementPoints(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        try {

            PointsCreateResponse pointsCreateResponse = pointService.incrementPoints(studentId, courseId);

            return new ResponseEntity<>(pointsCreateResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method retrieves point data for all students in a particular course
     *
     * @param courseId The ID of the course to retrieve point data for
     * @return A ResponseEntity containing the CourseStudentsResponse object and an HTTP status code of 200 OK on success, or an error message and an HTTP status code of 500 INTERNAL_SERVER_ERROR on failure
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getPointsByCourse(@PathVariable("courseId") String courseId) {
        try {
            CourseStudentsResponse courseStudentsResponse = pointService.dataOfStudent(courseId);
            return new ResponseEntity<>(courseStudentsResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This method retrieves point data for a student in all courses
     *
     * @param bannerId The banner ID of the student to retrieve point data for
     * @return A ResponseEntity containing a list of StudentPointsAllSubject objects and an HTTP status code of 200 OK on success, or an error message and an HTTP status code of 500 INTERNAL_SERVER_ERROR on failure
     */
    @GetMapping("/student/{bannerId}")
    public ResponseEntity<Object> getPointsByBannerId(@PathVariable("bannerId") String bannerId) {
        try {
            List<StudentPointsAllSubject> studentPointsByBannerId = pointService.pointsAllSubject(bannerId);
            return new ResponseEntity<>(studentPointsByBannerId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
