package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.*;
import com.GRP3.BPA.service.*;
import com.GRP3.BPA.service.CourseService;
import com.GRP3.BPA.service.CourseStudentService;
import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/courses")
@PreAuthorize("hasRole('TEACHER')")
public class TeacherCourseStudentController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getCourses(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            List<Course> teacherCourses = courseService.getCoursesForTeacher(teacherId);
            return new ResponseEntity<>(teacherCourses, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseRequest courseRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }

        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            Course course = courseService.addCourseForTeacher(teacherId, courseRequest);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCourses")
    public ResponseEntity<Object> addCourses(@RequestBody List<CourseRequest> courseRequests, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            List<Course> course = courseService.addCoursesForTeacher(teacherId, courseRequests);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeCourse")
    public ResponseEntity<Object> removeCourse(@RequestBody String courseId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            courseService.removeCourseForTeacher(teacherId, courseId);
            return new ResponseEntity<>("Course removed successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeCourses")
    public ResponseEntity<Object> removeCourses(@RequestBody List<String> courseIds, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            courseService.removeCoursesForTeacher(teacherId, courseIds);
            return new ResponseEntity<>("Courses removed Successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeStudent")
    public ResponseEntity<Object> removeStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            courseStudentService.removeStudent(teacherId, courseStudentRequest);
            return new ResponseEntity<>("Student removed successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            CourseStudent courseStudent = courseStudentService.addStudent(teacherId, courseStudentRequest);
            return new ResponseEntity<>(courseStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeStudents")
    public ResponseEntity<Object> removeStudents(@RequestBody CourseStudentRequests courseStudentRequests, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            courseStudentService.removeStudents(teacherId, courseStudentRequests);
            return new ResponseEntity<>("Student removed successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudents(@RequestBody CourseStudentRequests courseStudentRequests, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = jwtService.extractUsername(token);
            String teacherId = teacherService.findTeacherAssociatedWithUser(email);
            List<CourseStudent> courseStudent = courseStudentService.addStudents(teacherId, courseStudentRequests);
            return new ResponseEntity<>(courseStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{courseId}/{studentId}")
    public ResponseEntity<Object> incrementPoints(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        try {

            PointsCreateResponse pointsCreateResponse = courseStudentService.incrementPoints(studentId, courseId);
            ;
            return new ResponseEntity<>(pointsCreateResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/points/{courseId}")
    public ResponseEntity<Object> getPointsByCourse(@PathVariable("courseId") String courseId) {
        try {
            CourseStudentsResponse courseStudentsResponse = courseStudentService.dataOfStudent(courseId);
            return new ResponseEntity<>(courseStudentsResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
