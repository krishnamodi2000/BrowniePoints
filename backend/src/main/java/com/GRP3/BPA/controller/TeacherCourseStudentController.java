package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.GlobalException;
import com.GRP3.BPA.model.course.CourseRequest;
import com.GRP3.BPA.model.course.*;
import com.GRP3.BPA.model.courseStudent.*;
import com.GRP3.BPA.model.student.Student;
import com.GRP3.BPA.service.CourseService;
import com.GRP3.BPA.service.CourseStudentService;
import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping
    public ResponseEntity<Object> getCourses(@RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            List<Course> courses = courseService.getCoursesForTeacher(teacherId);
            CoursesResponse response = new CoursesResponse();
            response.setStatus(true);
            List<CourseRequest> courseRequestList = addCourseRequests(courses);
            response.setCourseRequestList(courseRequestList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseRequest courseRequest, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            Course course = courseService.addCourseForTeacher(teacherId, courseRequest);
            CourseRequest courseRequestToAdd = addCourseRequest(course);
            CourseResponse response = new CourseResponse(true, courseRequestToAdd);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addCourses")
    public ResponseEntity<Object> addCourses(@RequestBody List<CourseRequest> courseRequests, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            List<Course> courses = courseService.addCoursesForTeacher(teacherId, courseRequests);
            CoursesResponse response = new CoursesResponse();
            response.setStatus(true);
            List<CourseRequest> courseRequestList = addCourseRequests(courses);
            response.setCourseRequestList(courseRequestList);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeCourse")
    public ResponseEntity<Object> removeCourse(@RequestBody CourseIdRequest courseId, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {

            courseService.removeCourseForTeacher(teacherId, courseId.getCourseId());
            CourseResponse response = new CourseResponse();
            response.setStatus(true);
           //response.setCourseRequest(null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/removeCourses")
//    public ResponseEntity<Object> removeCourses(@RequestBody RemoveCourseDAO courseIds, @RequestHeader("Authorization") String authorizationHeader) {
//        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
//        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
//            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
//        }
//        String teacherId = teacherIdResponse.getBody();
//        try {
//            courseService.removeCoursesForTeacher(teacherId, courseIds.getCourseList());
//            CourseResponse response = new CourseResponse();
//            response.setStatus(true);
//            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (GlobalException e) {
//            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
//        }
//    }
//
    @DeleteMapping("/removeCourses")
    public ResponseEntity<Object> removeCourses(@RequestBody CourseIdsRequest courseIdsList, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            courseService.removeCoursesForTeacher(teacherId, courseIdsList.getCourseIds());
            CourseResponse response = new CourseResponse();
            response.setStatus(true);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/removeStudent")
    public ResponseEntity<Object> removeStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            courseStudentService.removeStudent(teacherId, courseStudentRequest);
            CourseStudentsResponse response = new CourseStudentsResponse();
            response.setStatus(true);

            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody CourseStudentRequest courseStudentRequest, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            CourseStudent courseStudent = courseStudentService.addStudent(teacherId, courseStudentRequest);
            CourseStudentResponse response = new CourseStudentResponse();
            response.setStatus(true);
            StudentInfoWithName studentInfoWithName=addedStudent(courseStudent);
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
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {

            courseStudentService.removeStudents(teacherId, courseStudentRequests);
            return new ResponseEntity<>("Student removed successfully", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addStudents")
    public ResponseEntity<Object> addStudents(@RequestBody CourseStudentRequests courseStudentRequests, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            List<CourseStudent> courseStudent = courseStudentService.addStudents(teacherId, courseStudentRequests);
            CourseStudentsResponse response=new CourseStudentsResponse();
            response.setStatus(true);
            ArrayList<StudentInfoWithName> studentInfoWithNames=addedStudents(courseStudent);
            response.setData(studentInfoWithNames);
            return new ResponseEntity<>(courseStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (GlobalException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{courseId}/{studentId}")
    public ResponseEntity<Object> incrementPoints(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId) {
        try {

            PointsCreateResponse pointsCreateResponse = courseStudentService.incrementPoints(studentId, courseId);

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

    public CourseRequest addCourseRequest(Course course) {

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName(course.getCourseName());
        courseRequest.setCourseId(course.getCourseId());
        courseRequest.setCourseDescription(course.getCourseDescription());

        return courseRequest;
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
        ArrayList<StudentInfoWithName> studentInfoWithNameList=new ArrayList<>();
        for(CourseStudent courseStudent:courseStudents){
            StudentInfoWithName studentInfoWithName=addedStudent(courseStudent);
            studentInfoWithNameList.add(studentInfoWithName);
        }
        return studentInfoWithNameList;
    }

    public List<CourseRequest> addCourseRequests(List<Course> courses) {
        List<CourseRequest> courseRequestList = new ArrayList<>();
        for (Course course : courses) {
            CourseRequest courseRequest = addCourseRequest(course);
            courseRequestList.add(courseRequest);
        }
        return courseRequestList;
    }

    public ResponseEntity<String> validateAuthorizationHeader(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String email = jwtService.extractUsername(token);
        String teacherId = teacherService.findTeacherAssociatedWithUser(email);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(teacherId);
    }
}
