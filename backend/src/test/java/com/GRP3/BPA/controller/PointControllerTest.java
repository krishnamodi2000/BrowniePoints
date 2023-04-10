package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.response.points.PointsCreateResponse;
import com.GRP3.BPA.service.PointServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class PointControllerTest {
    @InjectMocks
    PointServiceImpl pointService;
    @Mock
    CourseStudentRepository courseStudentRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    CourseRepository courseRepository;
    @Mock
    UserRepository userRepository;
    @Test
    public void testIncrementPointsSuccess() {
        Student student = new Student();
        Course course = new Course();
        User user = new User();
        user.setUserId("j123");
        userRepository.save(user);

        student.setBannerId("B008876");
        student.setUser(user);
        studentRepository.save(student);

        course.setCourseId("CSCIASDC");
        courseRepository.save(course);

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent.setPoints(1);
        courseStudentRepository.save(courseStudent);

        PointController pointController = new PointController();
        PointsCreateResponse pointsCreateResponse = pointService.incrementPoints(student.getBannerId(), course.getCourseId());
        ResponseEntity<Object> responseEntity = pointController.incrementPoints("student-123", "course-456");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        //Ass
    }
    @Test
    public void testIncrementPointsError() {
        Student student = new Student();
        Course course = new Course();
        User user = new User();
        user.setUserId("j123");
        userRepository.save(user);

        student.setBannerId("B008876");
        student.setUser(user);
        studentRepository.save(student);

        course.setCourseId("CSCIASDC");
        courseRepository.save(course);

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent.setPoints(1);
        courseStudentRepository.save(courseStudent);

        PointController pointController = new PointController();
        PointsCreateResponse pointsCreateResponse = pointService.incrementPoints(student.getBannerId(), course.getCourseId());
        ResponseEntity<Object> responseEntity = pointController.incrementPoints("student-123", "course-456");
        String errorMessage = (String) responseEntity.getBody();
        assertNotEquals("Invalid student or course ID", errorMessage);
    }

    @Test
    public void testGetPointsByCourseSuccess() {
        Course course = new Course();
        String courseId = "COURSE123";
        course.setCourseId(courseId);
        courseRepository.save(course);

        Student student = new Student();
        student.setBannerId("B005757");
        student.setUser(new User());
        studentRepository.save(student);

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setStudent(student);
        courseStudent.setPoints(1);

        PointController pointController = new PointController();
        ResponseEntity<Object> response = pointController.getPointsByCourse(course.getCourseId());
        assertNotEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}