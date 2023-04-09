package com.GRP3.BPA.controller;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.*;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import com.GRP3.BPA.request.course.CourseIdRequest;
import com.GRP3.BPA.request.course.CourseIdsRequest;
import com.GRP3.BPA.request.course.CourseRequest;
import com.GRP3.BPA.response.ExceptionResponse;
import com.GRP3.BPA.response.course.CourseResponse;
import com.GRP3.BPA.response.course.CoursesResponse;
import com.GRP3.BPA.service.CourseService;
import com.GRP3.BPA.service.CourseServiceImpl;
import com.GRP3.BPA.utils.JWTAuthenticationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @Mock
    private JWTAuthenticationUtil jwtAuthenticationUtil;

    @InjectMocks
    private CourseController courseController;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseStudentRepository courseStudentRepository;

    @Mock
    private CourseRepository courseRepository;
    private String validToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxODU0MzU4MywiZXhwIjoxNjE4NTQ3MTgzfQ.SB8eqx4z4jiv4pztLJNZAGiwZ18nGKj3g09S8ElmKX9Eifm0qgFq6w4Uf4K0o0GZ6xJUoFMNrd2NpuYIX-1JQ";

    private String teacherId = "teacher1";

    private Course course;

    private List<Course> courses;
    private Teacher teacher;
    private Student student;

    private Course course1;
    private User userIsTeacher;

    private User userIsStudent;

    private CourseStudent courseStudent;
    List<CourseRequest> courseRequestList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        userIsTeacher = new User();
        teacher = new Teacher();
        teacher.setTeacherId("1");
        teacher.setUser(userIsTeacher);

        userIsStudent = new User();
        student = new Student();
        student.setBannerId("1");
        student.setUser(userIsStudent);


        course = new Course();
        course.setCourseId("1");
        course.setCourseName("ASDC");
        course.setCourseDescription("A course on ASDC");
        course.setTeacher(teacher);

        course1 = new Course();
        course1.setCourseId("2");
        course1.setCourseName("Data Management");
        course1.setCourseDescription("A course on Data Management");
        course1.setTeacher(teacher);

        courseStudent = new CourseStudent();
        courseStudent.setCourse(course1);
        courseStudent.setStudent(student);
        courseStudent.setPoints(0);

    }

    @Test
    public void getCoursesValidTokenAndTeacherId() throws CustomizableException {

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("1");
        courseRequest.setCourseName("ASDC");
        courseRequest.setCourseDescription("A course on ASDC");

        CourseRequest courseRequest1 = new CourseRequest();
        courseRequest1.setCourseId("2");
        courseRequest1.setCourseName("Data Management");
        courseRequest1.setCourseDescription("A course on Data Management");

        courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);

        courseRequestList.add(courseRequest);
        courseRequestList.add(courseRequest1);

        when(jwtAuthenticationUtil.validateAuthorizationHeader(validToken)).thenReturn(new ResponseEntity<>("1", HttpStatus.OK));
        when(courseService.getCoursesForTeacher("1")).thenReturn(courses);

        ResponseEntity<Object> response = courseController.getCourses(validToken);

        verify(jwtAuthenticationUtil).validateAuthorizationHeader(validToken);
        verify(courseService).getCoursesForTeacher("1");

        CoursesResponse expectedResponse = new CoursesResponse();
        expectedResponse.setStatus(true);
        expectedResponse.setCourseRequestList(courseRequestList);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    public void getCoursesValidTokenAndInvalidTeacherId() throws CustomizableException {
//
//        when(jwtAuthenticationUtil.validateAuthorizationHeader(validToken)).thenReturn(new ResponseEntity<>("999", HttpStatus.OK));
//
//       // when(courseService.getCoursesForTeacher("999")).thenThrow(new CustomizableException(false,""));
//
//        // Call the API endpoint
//        ResponseEntity<Object> response = courseController.getCourses(validToken);
//
//        // Verify the response
//        ExceptionResponse exceptionResponse = new ExceptionResponse();
//        response.getBody();
//        verify(jwtAuthenticationUtil, times(1)).validateAuthorizationHeader(validToken);
//        verifyNoMoreInteractions(jwtAuthenticationUtil);
//        verify(courseService, times(1)).getCoursesForTeacher("999");
//        verifyNoMoreInteractions(courseService);
//      //  assert exceptionResponse.isStatus();
//        assert exceptionResponse.getMessage().equals("");
//        assert response.getStatusCode().equals(HttpStatus.BAD_REQUEST);
//        }

    @Test
    public void getCoursesValidTokenAndInvalidTeacherId() throws CustomizableException {
        // Set up mocks
        when(jwtAuthenticationUtil.validateAuthorizationHeader(validToken)).thenReturn(new ResponseEntity<>("999", HttpStatus.OK));
        when(courseService.getCoursesForTeacher("999")).thenThrow(new CustomizableException(false, "Invalid teacher ID"));

        // Call the API endpoint with an invalid teacher ID
        ResponseEntity<Object> response = courseController.getCourses(validToken);

        // Verify the response
        ExceptionResponse exceptionResponse = (ExceptionResponse) response.getBody();
        verify(jwtAuthenticationUtil, times(1)).validateAuthorizationHeader(validToken);
        verifyNoMoreInteractions(jwtAuthenticationUtil);
        verify(courseService, times(1)).getCoursesForTeacher("999");
        verifyNoMoreInteractions(courseService);
        assert !exceptionResponse.isStatus();
        assert exceptionResponse.getMessage().equals("Invalid teacher ID");
        assert response.getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }


}
