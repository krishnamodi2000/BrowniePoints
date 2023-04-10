package com.GRP3.BPA.controller;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.request.course.CourseRequest;
import com.GRP3.BPA.request.course.CourseIdRequest;
import com.GRP3.BPA.response.ExceptionResponse;
import com.GRP3.BPA.response.course.CourseResponse;
import com.GRP3.BPA.response.course.CoursesResponse;
import com.GRP3.BPA.service.ServiceInterface.CourseService;
import com.GRP3.BPA.utils.JWTAuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/courses")
//@PreAuthorize("hasRole('TEACHER')")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private JWTAuthenticationUtil jwtAuthenticationUtil;

    /**
     * Gets the list of courses for the teacher identified by the Authorization header.
     *
     * @param authorizationHeader the Authorization header value containing the JWT token
     * @return ResponseEntity containing the CoursesResponse object with the list of courses and the status
     */
    @GetMapping
    public ResponseEntity<Object> getCourses(@RequestHeader("Authorization") String authorizationHeader){
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
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
        } catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Adds a course for the teacher identified by the Authorization header.
     *
     * @param courseRequest the CourseRequest object containing the course information
     * @param authorizationHeader the Authorization header value containing the JWT token
     * @return ResponseEntity containing the CourseResponse object with the added course information and the status
     */
    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseRequest courseRequest, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            Course course = courseService.addCourseForTeacher(teacherId, courseRequest);
            CourseRequest courseRequestToAdd = addCourseRequest(course);
            CourseResponse response = new CourseResponse(true, courseRequestToAdd);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
    }
//
//    @PostMapping("/addCourses")
//    public ResponseEntity<Object> addCourses(@RequestBody List<CourseRequest> courseRequests, @RequestHeader("Authorization") String authorizationHeader) {
//        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
//        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
//            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
//        }
//        String teacherId = teacherIdResponse.getBody();
//        try {
//            List<Course> courses = courseService.addCoursesForTeacher(teacherId, courseRequests);
//            CoursesResponse response = new CoursesResponse();
//            response.setStatus(true);
//            List<CourseRequest> courseRequestList = addCourseRequests(courses);
//            response.setCourseRequestList(courseRequestList);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (CustomizableException e) {
//            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
//            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//        }
//    }

    /**
     * Removes a course for the teacher identified by the Authorization header.
     *
     * @param courseId the CourseIdRequest object containing the course ID to remove
     * @param authorizationHeader the Authorization header value containing the JWT token
     * @return ResponseEntity containing the CourseResponse object with the status
     */
    @DeleteMapping("/removeCourse")
    public ResponseEntity<Object> removeCourse(@RequestBody CourseIdRequest courseId, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
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
        } catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
    }

//
//    @DeleteMapping("/removeCourses")
//    public ResponseEntity<Object> removeCourses(@RequestBody CourseIdsRequest courseIdsList, @RequestHeader("Authorization") String authorizationHeader) {
//        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
//        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
//            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
//        }
//        String teacherId = teacherIdResponse.getBody();
//        try {
//            courseService.removeCoursesForTeacher(teacherId, courseIdsList.getCourseIds());
//            CourseResponse response = new CourseResponse();
//            response.setStatus(true);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (CustomizableException e) {
//            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
//            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//        }
//    }

    /**
     * Endpoint for updating a course for a teacher.
     *
     * @param courseRequest The request body containing information about the course to update.
     * @param authorizationHeader The authorization header containing the JWT token.
     * @return A ResponseEntity containing either a CourseResponse or an ExceptionResponse.
     */
    @PutMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestBody CourseRequest courseRequest, @RequestHeader("Authorization") String authorizationHeader){
        ResponseEntity<String> teacherIdResponse = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        if (teacherIdResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(teacherIdResponse.getBody(), teacherIdResponse.getStatusCode());
        }
        String teacherId = teacherIdResponse.getBody();
        try {
            Course course=courseService.updateCourseForTeacher(teacherId, courseRequest);
            CourseRequest courseUpdated = addCourseRequest(course);
            CourseResponse response = new CourseResponse(true, courseUpdated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }  catch (CustomizableException e) {
            ExceptionResponse exceptionResponse=new ExceptionResponse(e.isStatus(),e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Helper method for converting a Course object to a CourseRequest object.
     *
     * @param course The Course object to convert.
     * @return A CourseRequest object.
     */
    public CourseRequest addCourseRequest(Course course) {

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName(course.getCourseName());
        courseRequest.setCourseId(course.getCourseId());
        courseRequest.setCourseDescription(course.getCourseDescription());

        return courseRequest;
    }

    /**
     * Helper method for converting a List of Course objects to a List of CourseRequest objects.
     *
     * @param courses The List of Course objects to convert.
     * @return A List of CourseRequest objects.
     */
    public List<CourseRequest> addCourseRequests(List<Course> courses) {
        List<CourseRequest> courseRequestList = new ArrayList<>();
        for (Course course : courses) {
            CourseRequest courseRequest = addCourseRequest(course);
            courseRequestList.add(courseRequest);
        }
        return courseRequestList;
    }
}
