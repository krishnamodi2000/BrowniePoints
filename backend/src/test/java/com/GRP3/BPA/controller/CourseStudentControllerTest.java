package com.GRP3.BPA.controller;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.*;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;
import com.GRP3.BPA.response.courseStudent.CourseStudentResponse;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.response.courseStudent.StudentInfoWithName;
import com.GRP3.BPA.service.ServiceInterface.CourseStudentService;
import com.GRP3.BPA.utils.JWTAuthenticationUtil;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)

@RunWith(SpringRunner.class)
public class CourseStudentControllerTest {


    @Mock
    private TeacherRepository teacherRepository;
     String validToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxODU0MzU4MywiZXhwIjoxNjE4NTQ3MTgzfQ.SB8eqx4z4jiv4pztLJNZAGiwZ18nGKj3g09S8ElmKX9Eifm0qgFq6w4Uf4K0o0GZ6xJUoFMNrd2NpuYIX-1JQ";

    @Mock
    private JWTAuthenticationUtil jwtAuthenticationUtil;
    @Mock
    private CourseStudentRepository courseStudentRepository;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseStudentService courseStudentService;
    @InjectMocks
    private CourseStudentController courseStudentController;
    private Teacher teacher;
    private Student student;
    private Student student1;
    private Course course1;
    private Course course2;
    private User userIsTeacher;

    private User userIsStudent1;
    private User userIsStudent2;

    private CourseStudent courseStudent1;

    private CourseStudent courseStudent2;

    private CourseStudent courseStudent3;

    List<Course> courses = new ArrayList<>();

    List<CourseStudent> courseStudents=new ArrayList<>();

    @BeforeEach
    public void setUp() {
        userIsTeacher = new User();
        teacher = new Teacher();
        teacher.setTeacherId("1");
        teacher.setUser(userIsTeacher);

        userIsStudent1 = new User();
        student=new Student();
        student.setBannerId("1");
        student.setUser(userIsStudent1);

        userIsStudent2 = new User();
        student1=new Student();
        student1.setBannerId("1");
        student1.setUser(userIsStudent2);

        course1 = new Course();
        course1.setCourseId("1");
        course1.setCourseName("ASDC");
        course1.setCourseDescription("A course on ASDC");
        course1.setTeacher(teacher);

        course2 = new Course();
        course2.setCourseId("2");
        course2.setCourseName("Data Management");
        course2.setCourseDescription("A course on Data Management");
        course2.setTeacher(teacher);

        courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        courseStudent1=new CourseStudent();
        courseStudent1.setCourse(course1);
        courseStudent1.setStudent(student);
        courseStudent1.setPoints(0);

        courseStudent2=new CourseStudent();
        courseStudent2.setCourse(course1);
        courseStudent2.setStudent(student1);
        courseStudent2.setPoints(0);

        courseStudent3 =new CourseStudent();
        courseStudent3.setCourse(course2);
        courseStudent3.setStudent(student);
        courseStudent3.setPoints(0);

    }

    @Test
    public void testRemoveStudent() throws CustomizableException {
        // Arrange
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        when(jwtAuthenticationUtil.validateAuthorizationHeader(validToken)).thenReturn(new ResponseEntity<>("1", HttpStatus.OK));
        courseStudentService.removeStudent("1", courseStudentRequest);

        // Act
        ResponseEntity<Object> responseEntity = courseStudentController.removeStudent(courseStudentRequest, validToken);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof CourseStudentsResponse);
        CourseStudentsResponse response = (CourseStudentsResponse) responseEntity.getBody();
        assertTrue(response.isStatus());
    }
    @Test
    public void testRemoveStudents() throws CustomizableException {
        // Arrange
        CourseStudentRequests requestList = new CourseStudentRequests("1", Arrays.asList("1","2"));



        when(jwtAuthenticationUtil.validateAuthorizationHeader(validToken)).thenReturn(new ResponseEntity<>("1", HttpStatus.OK));
        doNothing().when(courseStudentService).removeStudents("1", requestList);

        // Act
        ResponseEntity<Object> responseEntity = courseStudentController.removeStudents(requestList, validToken);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof CourseStudentResponse);
        CourseStudentResponse response = (CourseStudentResponse) responseEntity.getBody();
        assertTrue(response.isStatus());
    }

    @Test
    public void testAddStudents() throws CustomizableException {
        // Arrange
        CourseStudentRequests courseStudentRequests = new CourseStudentRequests("1", Arrays.asList("1","2"));

        when(jwtAuthenticationUtil.validateAuthorizationHeader(validToken)).thenReturn(new ResponseEntity<>("1", HttpStatus.OK));

        Student student = new Student();
        student.setBannerId("1");
        User user1 = new User();
        student.setUser(user1);

        Student student1 = new Student();
        student1.setBannerId("2");
        User user2 = new User();
        student1.setUser(user2);

        Course course1 = new Course();
        course1.setCourseId("1");

        Course course2 = new Course();
        course2.setCourseId("2");

        List<CourseStudent> courseStudents = new ArrayList<>();
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course1);
        courseStudent.setStudent(student);
        courseStudent.setPoints(0);
        courseStudents.add(courseStudent);

        CourseStudent courseStudent1 = new CourseStudent();
        courseStudent1.setCourse(course2);
        courseStudent1.setStudent(student1);
        courseStudent1.setPoints(0);
        courseStudents.add(courseStudent1);

        when(courseStudentService.addStudents("1", courseStudentRequests)).thenReturn(courseStudents);

        // Act
        ResponseEntity<Object> responseEntity = courseStudentController.addStudents(courseStudentRequests, validToken);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof CourseStudentsResponse);
        CourseStudentsResponse response = (CourseStudentsResponse) responseEntity.getBody();
        assertTrue(response.isStatus());
        List<StudentInfoWithName> data = response.getData();
        assertNotNull(data);
        assertEquals(2, data.size());
    }


}
