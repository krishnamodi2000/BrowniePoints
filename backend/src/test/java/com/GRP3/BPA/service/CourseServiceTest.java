package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.*;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.request.course.CourseRequest;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CourseServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseStudentRepository courseStudentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;
    private Teacher teacher;
    private Student student;
    private Course course;
    private Course course1;
    private User userIsTeacher;

    private User userIsStudent;

    private CourseStudent courseStudent;
    List<Course> courses = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        userIsTeacher = new User();
        teacher = new Teacher();
        teacher.setTeacherId("1");
        teacher.setUser(userIsTeacher);

        userIsStudent = new User();
        student=new Student();
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

        courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);

        courseStudent=new CourseStudent();
        courseStudent.setCourse(course1);
        courseStudent.setStudent(student);
        courseStudent.setPoints(0);


    }

    @Test
    public void getCoursesForTeacher() throws CustomizableException {
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherId("1")).thenReturn(courses);
        // Call the service method
        List<Course> result = courseService.getCoursesForTeacher("1");

        // Verify the result
        Assertions.assertEquals(courses, result, "The returned courses should match the expected courses.");

        // Verify that the repository method was called once with the correct argument
        verify(courseRepository, times(1)).findByTeacherTeacherId(teacher.getTeacherId());
    }

    @Test
    public void testGetCoursesIfTeacherDoesNotExists(){
        when(teacherRepository.findByTeacherId("1")).thenReturn(null);

        // Call the service method and expect an exception to be thrown
        Assertions.assertThrows(CustomizableException.class, () -> courseService.getCoursesForTeacher("1"),
                "The method should throw a CustomizableException when the teacher doesn't take any courses.");

        // Verify that the repository method was called once with the correct argument
        verify(courseRepository,never()).findByTeacherTeacherId("1");
    }

    @Test
    void getCoursesForTeacherWhenTeacherHasNoCourses() {
        // Arrange
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherId(teacher.getTeacherId())).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(CustomizableException.class, () -> courseService.getCoursesForTeacher(teacher.getTeacherId()));
        verify(courseRepository, times(1)).findByTeacherTeacherId(teacher.getTeacherId());
    }
    @Test
    public void testAddCourseForTeacher() throws CustomizableException {
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
    public void testAddCourseForTeacherIfTeacherDoesNotExists() throws CustomizableException {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("3");
        courseRequest.setCourseName("Communicating Computer Science Ideas");
        courseRequest.setCourseDescription("A course on technical communication");

        when(teacherRepository.findByTeacherId("1")).thenReturn(null);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseService.addCourseForTeacher("1", courseRequest);
        });
    }

    @Test
    public void testAddCourseForTeacherIfCourseExists() throws CustomizableException {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("2");
        courseRequest.setCourseName("Data Management");
        courseRequest.setCourseDescription("A course on Data Management");

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByCourseId(courseRequest.getCourseId())).thenReturn(course1);

        // Call the service method and expect an exception to be thrown
        Assertions.assertThrows(CustomizableException.class, () -> courseService.addCourseForTeacher("1", courseRequest),
                "The method should throw a CustomizableException when trying to add a course that already exists.");

        // Verify that the repository method was called once with the correct argument
        verify(courseRepository, times(1)).findByCourseId(courseRequest.getCourseId());
    }
    @Test
    public void testAddCoursesForTeacher() throws CustomizableException {

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.saveAll(any(List.class))).thenReturn(courses);
        List<CourseRequest> courseRequestList = new ArrayList<>();

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("1");
        courseRequest.setCourseName("ASDC");
        courseRequest.setCourseDescription("A course on ASDC");

        CourseRequest courseRequest1 = new CourseRequest();
        courseRequest1.setCourseId("2");
        courseRequest1.setCourseName("Data Management");
        courseRequest1.setCourseDescription("A course on Data Management");
        courseRequestList.add(courseRequest);
        courseRequestList.add(courseRequest1);
        List<Course> result = courseService.addCoursesForTeacher("1", courseRequestList);
        Assertions.assertEquals(courses,result);
    }

    @Test
    public void testAddCoursesForTeacherIfTeacherDoesNotExists() throws CustomizableException {
        List<CourseRequest> courseRequestList = new ArrayList<>();

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("1");
        courseRequest.setCourseName("ASDC");
        courseRequest.setCourseDescription("A course on ASDC");

        CourseRequest courseRequest1 = new CourseRequest();
        courseRequest1.setCourseId("2");
        courseRequest1.setCourseName("Data Management");
        courseRequest1.setCourseDescription("A course on Data Management");
        courseRequestList.add(courseRequest);
        courseRequestList.add(courseRequest1);

        when(teacherRepository.findByTeacherId("1")).thenReturn(null);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseService.addCoursesForTeacher("1", courseRequestList);
        });
    }

    @Test
    public void testAddCoursesForTeacherIfCourseExists() throws CustomizableException {
        List<CourseRequest> courseRequestList = new ArrayList<>();

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseId("1");
        courseRequest.setCourseName("ASDC");
        courseRequest.setCourseDescription("A course on ASDC");

        CourseRequest courseRequest1 = new CourseRequest();
        courseRequest1.setCourseId("2");
        courseRequest1.setCourseName("Data Management");
        courseRequest1.setCourseDescription("A course on Data Management");
        courseRequestList.add(courseRequest);
        courseRequestList.add(courseRequest1);

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByCourseId(courseRequest.getCourseId())).thenReturn(course);
        when(courseRepository.findByCourseId(courseRequest.getCourseId())).thenReturn(course1);

        // Call the service method and expect an exception to be thrown
        Assertions.assertThrows(CustomizableException.class, () -> courseService.addCoursesForTeacher("1", courseRequestList),
                "The method should throw a CustomizableException when trying to add a course that already exists.");

        // Verify that the repository method was called once with the correct argument
        verify(courseRepository, times(1)).findByCourseId(courseRequest.getCourseId());
    }

    //check this test
    @Test
    public void testRemoveCourseForTeacher() throws CustomizableException {
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1", "2")).thenReturn(course1);
        when(courseStudentRepository.findByCourseCourseId("2")).thenReturn(Collections.emptyList());

        courseService.removeCourseForTeacher("1", "2");

        Assertions.assertNull(courseRepository.findByCourseId("2"));
        verify(courseRepository, times(1)).delete(course1);
    }


    //check test case
    @Test
    public void testRemoveCourseForTeacherInvalidCourse() {
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1", "3")).thenReturn(null);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseService.removeCourseForTeacher("1", "3");
        });

    }

    @Test
    public void testRemoveCourseForTeacherInvalidTeacher() {
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseService.removeCourseForTeacher("1", "2");
        });

    }

    @Test
    public void testRemoveCoursesForTeacher() throws CustomizableException {
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1", "1")).thenReturn(course);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1", "2")).thenReturn(course1);

        // Call method being tested
        courseService.removeCoursesForTeacher("1", Arrays.asList("1","2"));

        // Verify that courses were deleted
        verify(courseRepository, times(1)).deleteAll(courses);
    }

    @Test
    public void testRemoveCoursesForTeacherInvalidCourse() {
        when(courseRepository.findByTeacherTeacherIdAndCourseIdIn("1", Arrays.asList("1","3"))).thenReturn(null);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseService.removeCoursesForTeacher("1", Arrays.asList("1", "3"));
        });

    }

    @Test
    public void testRemoveCoursesForTeacherInvalidTeacher() {

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseService.removeCoursesForTeacher("1", Arrays.asList("1", "2"));
        });

    }
}