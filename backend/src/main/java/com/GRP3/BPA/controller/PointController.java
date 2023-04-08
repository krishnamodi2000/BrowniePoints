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
@RequestMapping("/api/teachers/points/")
public class PointController {
    @Autowired
    private PointServiceImpl pointService;

    @PutMapping("{courseId}/{studentId}")
    public ResponseEntity<Object> incrementPoints(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        try {

            PointsCreateResponse pointsCreateResponse = pointService.incrementPoints(studentId, courseId);

            return new ResponseEntity<>(pointsCreateResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{courseId}")
    public ResponseEntity<Object> getPointsByCourse(@PathVariable("courseId") String courseId) {
        try {
            CourseStudentsResponse courseStudentsResponse = pointService.dataOfStudent(courseId);
            return new ResponseEntity<>(courseStudentsResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{bannerId}")
    public ResponseEntity<Object> getPointsByBannerId(@PathVariable("bannerId") String bannerId) {
        try {
            List<StudentPointsAllSubject> studentPointsByBannerId = pointService.pointsAllSubject(bannerId);
            return new ResponseEntity<>(studentPointsByBannerId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
