package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseRequest;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.CourseStudentRequest;
import com.GRP3.BPA.service.CourseService;
import com.GRP3.BPA.service.CourseStudentService;
import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/courses")
public class TeacherCourseStudentController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtService jwtService;

        @GetMapping
    public ResponseEntity<Object> getCourses(@RequestParam String teacherId) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }
        try {
            List<Course> teacherCourses = courseService.getCoursesForTeacher(teacherId);
            return new ResponseEntity<>(teacherCourses, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping
//    public ResponseEntity<Object> getCourses(@RequestHeader("Authorization") String authorizationHeader) {
////        if (!isValidToken(token)) {
////            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
////        }
//        String token = authorizationHeader.substring(7);
//        String userId= jwtService.extractUserEmail(token);
//        String teacherId= teacherService.isUserAssociatedWithTeacher(userId);
//        try {
//            List<Course> teacherCourses = courseService.getCoursesForTeacher(teacherId);
//            return new ResponseEntity<>(teacherCourses, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseRequest courseRequest) {
//        UserDetails userDetails = jwtService.extractUserDetails(token);
//        if (!jwtService.isTokenValid(token,userDetails)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            Course course = courseService.addCourseForTeacher(courseRequest);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCourses")
    public ResponseEntity<Object> addCourses(@RequestBody List<CourseRequest> courseRequests) {
//        UserDetails userDetails = jwtService.extractUserDetails(token);
//        if (!jwtService.isTokenValid(token,userDetails)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            List<Course> course = courseService.addCoursesForTeacher(courseRequests);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
        @DeleteMapping("/removeCourse")
    public ResponseEntity<Object> removeCourse(@RequestBody CourseRequest courseRequest) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            courseService.removeCourseForTeacher(courseRequest);
            return new ResponseEntity<>("Course removed successfully",HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
        @DeleteMapping("/removeCourses")
    public ResponseEntity<Object> removeCourses(@RequestBody List<CourseRequest> courseRequests) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            courseService.removeCoursesForTeacher(courseRequests);
            return new ResponseEntity<>("Courses removed Successfully",HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeStudent")
    public ResponseEntity<Object> removeStudent(@RequestBody CourseStudentRequest courseStudentRequest) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            courseStudentService.removeStudent(courseStudentRequest);
            return new ResponseEntity<>("Student removed successfully",HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody CourseStudentRequest courseStudentRequest) {
//        UserDetails userDetails = jwtService.extractUserDetails(token);
//        if (!jwtService.isTokenValid(token,userDetails)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            CourseStudent courseStudent = courseStudentService.addStudent(courseStudentRequest);
            return new ResponseEntity<>(courseStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/addStudents")
//    public ResponseEntity<Object> addStudentsFromCsv(@RequestBody File file) {
//        try {
//            courseStudentService.addStudentsFromCsv(file);
//            return new ResponseEntity<>("Students Added Succesfully",HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }
}
