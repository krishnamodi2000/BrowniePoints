package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.TeacherCourse;
import com.GRP3.BPA.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers/{teacherId}/courses")
public class TeacherController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    @PostMapping("/{courseId}")
    public ResponseEntity<Object> addCourse(@PathVariable String teacherId, @PathVariable String courseId) {
        try {
            TeacherCourse teacherCourse = teacherCourseService.addCourseForTeacher(teacherId, courseId);
            return new ResponseEntity<>(teacherCourse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCourses(@PathVariable String teacherId, @RequestBody List<String> courseIds) {
        try {
            List<TeacherCourse> teacherCourses = teacherCourseService.addCoursesForTeacher(teacherId, courseIds);
            return new ResponseEntity<>(teacherCourses, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{teacherCourseId}")
    public ResponseEntity<Object> removeCourse(@PathVariable Long teacherCourseId) {
        try {
            teacherCourseService.removeCourseForTeacher(teacherCourseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> removeCourses(@PathVariable String teacherId, @RequestBody List<String> courseIds) {
        try {
            teacherCourseService.removeCoursesForTeacher(teacherId, courseIds);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getCourses(@PathVariable String teacherId) {
        try {
            List<TeacherCourse> teacherCourses = teacherCourseService.getCoursesForTeacher(teacherId);
            return new ResponseEntity<>(teacherCourses, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
