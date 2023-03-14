package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.TeacherCourse;
import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
/** import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
 */
import java.util.List;


/**
 * Internal filter ????????? How is it done and how do I extend it to my method!
 */
@RestController
@RequestMapping("/teachers/courses")
public class TeacherController {
    JwtService jwtService;
    @Autowired
    private TeacherCourseService teacherCourseService;
    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody String teacherId, @RequestBody String courseId,@RequestHeader("Authorization") String token) {
//        UserDetails userDetails = jwtService.extractUserDetails(token);
//        if (!jwtService.isTokenValid(token,userDetails)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            TeacherCourse teacherCourse = teacherCourseService.addCourseForTeacher(teacherId, courseId);
            return new ResponseEntity<>(teacherCourse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCourses")
    public ResponseEntity<Object> addCourses(@RequestBody String teacherId, @RequestBody List<String> courseIds) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            List<TeacherCourse> teacherCourses = teacherCourseService.addCoursesForTeacher(teacherId, courseIds);
            return new ResponseEntity<>(teacherCourses, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeCourse")
    public ResponseEntity<Object> removeCourse(@RequestBody String teacherId, @RequestBody String courseId) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            teacherCourseService.removeCourseForTeacher(teacherId,courseId);
            return new ResponseEntity<>("Course removed successfully",HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> removeCourses(@PathVariable String teacherId, @RequestBody List<String> courseIds) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }

        try {
            teacherCourseService.removeCoursesForTeacher(teacherId, courseIds);
            return new ResponseEntity<>("Courses removed Successfully",HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getCourses(@RequestBody String teacherId) {
//        if (!isValidToken(token)) {
//            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
//        }
        try {
            List<TeacherCourse> teacherCourses = teacherCourseService.getCoursesForTeacher(teacherId);
            return new ResponseEntity<>(teacherCourses, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
