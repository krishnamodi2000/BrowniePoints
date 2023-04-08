package com.GRP3.BPA.service;

import com.GRP3.BPA.model.User;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.exceptions.GlobalException;
import com.GRP3.BPA.request.course.CourseRequest;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;
    private Teacher teacher;

    private Course course;
    private Course course1;
    private User user;
    private CourseRequest courseRequest;
    private CourseRequest courseRequest1;

    List<Course> courses=new ArrayList<>();
    @BeforeEach
    public void setUp()  {

        user=new User();
        teacher = new Teacher();
        teacher.setTeacherId("1");
        teacher.setUser(user);

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

        courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);


    }

    @Test
    public void getCoursesForTeacher() throws GlobalException {

        when(courseRepository.findByTeacherTeacherId("1")).thenReturn(courses);

        List<Course> result = courseService.getCoursesForTeacher("1");

        for (int i = 0; i < courses.size(); i++) {
            Assertions.assertEquals(courses.get(i).getCourseId(), result.get(i).getCourseId());
        }
        verify(courseRepository, times(1)).findByTeacherTeacherId(teacher.getTeacherId());
    }

    @Test
    public void testGetCoursesForTeacherEmpty() throws GlobalException {
        when(courseRepository.findByTeacherTeacherId("1")).thenReturn(new ArrayList<>());
        Assertions.assertThrows(GlobalException.class, () -> {
            courseService.getCoursesForTeacher(teacher.getTeacherId());
        });
    }
    @Test
    public void testAddCourseForTeacher() throws GlobalException {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("2");
        courseRequest.setCourseName("Data Management");
        courseRequest.setCourseDescription("A course on Data Management");

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.save(any(Course.class))).thenReturn(course1);

        Course result = courseService.addCourseForTeacher("1", courseRequest);

        Assertions.assertEquals(course1.getCourseId(), result.getCourseId());
    }

    @Test
    public void testAddCourseForTeacherInvalidTeacher() throws GlobalException {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("3");
        courseRequest.setCourseName("Communicating Computer Science Ideas");
        courseRequest.setCourseDescription("A course on technical communication");

        when(teacherRepository.findByTeacherId("1")).thenReturn(null);
        Assertions.assertThrows(GlobalException.class, () -> {
            courseService.addCourseForTeacher("1", courseRequest);});
    }

    @Test
    public void testAddCoursesForTeacher() throws GlobalException {

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.saveAll(any(List.class))).thenReturn(courses);
        List<CourseRequest> courseRequestList=new ArrayList<>();

        courseRequest = new CourseRequest();
        courseRequest.setCourseId("1");
        courseRequest.setCourseName("ASDC");
        courseRequest.setCourseDescription("A course on ASDC");

        courseRequest1 = new CourseRequest();
        courseRequest1.setCourseId("2");
        courseRequest1.setCourseName("Data Management");
        courseRequest1.setCourseDescription("A course on Data Management");
        courseRequestList.add(courseRequest);
        courseRequestList.add(courseRequest1);
        List<Course> result = courseService.addCoursesForTeacher("1", courseRequestList);

        for (int i = 0; i < courses.size(); i++) {
            Assertions.assertEquals(courses.get(i).getCourseId(), result.get(i).getCourseId());
        }
    }

    //check this test
    @Test
    public void testRemoveCourseForTeacher() throws GlobalException {
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1", "1")).thenReturn(course);

        courseService.removeCourseForTeacher("1", "1");

        verify(courseRepository,times(1)).delete(course);
    }

//check test case
    @Test
    public void testRemoveCourseForTeacherInvalidCourse() {
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1", "3")).thenReturn(null);
        Assertions.assertThrows(GlobalException.class, () -> {
            courseService.removeCourseForTeacher("1", "3");});

    }
    @Test
    public void testRemoveCoursesForTeacher() throws GlobalException {
        // Mocking the course repository to return a list of courses
        when(courseRepository.findByTeacherTeacherIdAndCourseIdIn("1", Arrays.asList("1", "2"))).thenReturn(Arrays.asList(course, course1));

        // Calling the method being tested
        courseService.removeCoursesForTeacher("1", Arrays.asList("1", "2"));

        // Verifying that the deleteAll method was called on the course repository
        verify(courseRepository, times(1)).deleteAll(Arrays.asList(course, course1));
    }
    @Test
    public void testRemoveCoursesForTeacherInvalidCourse(){
        when(courseRepository.findByTeacherTeacherIdAndCourseIdIn("1", Arrays.asList("1", "3"))).thenReturn(Collections.emptyList());

        Assertions.assertThrows(GlobalException.class, () -> {
        courseService.removeCoursesForTeacher("1", Arrays.asList("1", "3"));});

    }
}