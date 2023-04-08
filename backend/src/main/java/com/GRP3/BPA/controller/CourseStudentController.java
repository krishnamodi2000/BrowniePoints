package com.GRP3.BPA.controller;

import com.GRP3.BPA.exceptions.GlobalException;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;
import com.GRP3.BPA.response.courseStudent.CourseStudentResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.response.courseStudent.StudentInfoWithName;
import com.GRP3.BPA.service.CourseStudentService;
import com.GRP3.BPA.utils.JWTAuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/courses/students")
public class CourseStudentController {
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private JWTAuthenticationUtil jwtAuthenticationUtil;

    @DeleteMapping("/removeStudent")
    public ResponseEntity<Object> removeStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            courseStudentService.removeStudent(teacherId, courseStudentRequest);
            CourseStudentsResponse response = new CourseStudentsResponse();
            response.setStatus(true);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            CourseStudent courseStudent = courseStudentService.addStudent(teacherId, courseStudentRequest);
            CourseStudentResponse response = new CourseStudentResponse();
            response.setStatus(true);
            StudentInfoWithName studentInfoWithName = addedStudent(courseStudent);
            response.setData(studentInfoWithName);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeStudents")
    public ResponseEntity<Object> removeStudents(@RequestBody CourseStudentRequests courseStudentRequests, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {

            courseStudentService.removeStudents(teacherId, courseStudentRequests);
            CourseStudentResponse response = new CourseStudentResponse();
            response.setStatus(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addStudents")
    public ResponseEntity<Object> addStudents(@RequestBody CourseStudentRequests courseStudentRequests, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            List<CourseStudent> courseStudent = courseStudentService.addStudents(teacherId, courseStudentRequests);
            CourseStudentsResponse response = new CourseStudentsResponse();
            response.setStatus(true);
            ArrayList<StudentInfoWithName> studentInfoWithNames = addedStudents(courseStudent);
            response.setData(studentInfoWithNames);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    public StudentInfoWithName addedStudent(CourseStudent courseStudent) {
        Student student = courseStudent.getStudent();
        StudentInfoWithName studentInfoWithName = new StudentInfoWithName();
        studentInfoWithName.setStudentName(student.getUser().getFirstName() + " " + student.getUser().getLastName());
        studentInfoWithName.setBannerId(student.getBannerId());
        studentInfoWithName.setPoints(courseStudent.getPoints());
        return (studentInfoWithName);
    }

    public ArrayList<StudentInfoWithName> addedStudents(List<CourseStudent> courseStudents) {
        ArrayList<StudentInfoWithName> studentInfoWithNameList = new ArrayList<>();
        for (CourseStudent courseStudent : courseStudents) {
            StudentInfoWithName studentInfoWithName = addedStudent(courseStudent);
            studentInfoWithNameList.add(studentInfoWithName);
        }
        return studentInfoWithNameList;
    }

}
