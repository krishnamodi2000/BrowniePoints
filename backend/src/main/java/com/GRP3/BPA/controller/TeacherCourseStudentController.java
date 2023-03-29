package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.*;
import com.GRP3.BPA.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teachers/courses")
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
            List<Course> course = courseService.addCoursesForTeacher(teacherId,courseRequests);
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
            courseService.removeCourseForTeacher(teacherId,courseId);
            return new ResponseEntity<>("Course removed successfully",HttpStatus.NO_CONTENT);
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
            courseService.removeCoursesForTeacher(teacherId,courseIds);
            return new ResponseEntity<>("Courses removed Successfully",HttpStatus.NO_CONTENT);
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
            courseStudentService.removeStudent(teacherId,courseStudentRequest);
            return new ResponseEntity<>("Student removed successfully",HttpStatus.NO_CONTENT);
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
            String email= jwtService.extractUsername(token);
            String teacherId= teacherService.findTeacherAssociatedWithUser(email);
            CourseStudent courseStudent = courseStudentService.addStudent(teacherId,courseStudentRequest);
            return new ResponseEntity<>(courseStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
