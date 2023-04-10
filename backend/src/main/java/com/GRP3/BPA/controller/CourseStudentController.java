package com.GRP3.BPA.controller;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;
import com.GRP3.BPA.response.ExceptionResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.response.courseStudent.StudentInfoWithName;
import com.GRP3.BPA.service.ServiceInterface.CourseStudentService;
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

//    @GetMapping("/getStudentsForTeacher")
//    public ResponseEntity<Object> getStudentsForACourse(@RequestHeader("Authorization") String authorizationHeader, @RequestBody CourseIdRequest courseIdRequest){
//        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
//        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
//            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
//        }
//        String teacherId = teacherIdResponse.getBody();
//        try {
//            String courseId= courseIdRequest.getCourseId();
//            List<CourseStudent> courseStudents = courseStudentService.getStudentsForACourse(teacherId,courseId);
//            CourseStudentsResponse response = new CourseStudentsResponse();
//            response.setStatus(true);
//            ArrayList<StudentInfoWithName> studentInfoWithNames = addedStudents(courseStudents);
//            response.setData(studentInfoWithNames);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (CustomizableException e) {
//            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
//            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//        }
//    }

    /**
     * Removes a student from a course.
     *
     * @param courseStudentRequest The request object containing the student and course IDs.
     * @param authorizationHeader  The authorization header containing the JWT token for the teacher.
     * @return A ResponseEntity with the status of the operation.
     */
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
        } catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/addStudent")
//    public ResponseEntity<Object> addStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
//        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
//        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
//            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
//        }
//        String teacherId = teacherIdResponse.getBody();
//        try {
//            CourseStudent courseStudent = courseStudentService.addStudent(teacherId, courseStudentRequest);
//            CourseStudentResponse response = new CourseStudentResponse();
//            response.setStatus(true);
//            StudentInfoWithName studentInfoWithName = addedStudent(courseStudent);
//            response.setData(studentInfoWithName);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (CustomizableException e) {
//            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
//            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//        }
//    }


    /**
     * Removes multiple students from a course.
     *
     * @param courseStudentRequests The request object containing the student and course IDs.
     * @param authorizationHeader   The authorization header containing the JWT token for the teacher.
     * @return A ResponseEntity with the status of the operation.
     */
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
        } catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
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
        }  catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
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
