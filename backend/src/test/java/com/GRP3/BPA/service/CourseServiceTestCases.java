//package com.GRP3.BPA.service;
//
//
//import com.GRP3.BPA.exceptions.CustomizableException;
//import com.GRP3.BPA.model.Course;
//import com.GRP3.BPA.model.Teacher;
//import com.GRP3.BPA.repository.CourseRepository;
//import com.GRP3.BPA.repository.TeacherRepository;
//import com.GRP3.BPA.repository.UserRepository;
//import com.GRP3.BPA.request.course.CourseRequest;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//public class CourseServiceTestCases {
//    @Mock
//    private TeacherRepository teacherRepository;
//
//    @Mock
//    private CourseRepository courseRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private CourseService courseService;
//
//    @Test
//    public void getCoursesForTeacher_whenValidTeacherId_shouldReturnListOfCourses() throws CustomizableException {
//        // Arrange
//        String teacherId = "1";
//        List<Course> expectedCourses = new ArrayList<>();
//        expectedCourses.add(new Course());
//        when(courseRepository.findByTeacherTeacherId(teacherId)).thenReturn(expectedCourses);
//
//        // Act
//        List<Course> actualCourses = courseService.getCoursesForTeacher(teacherId);
//
//        // Assert
//        assertEquals(expectedCourses, actualCourses);
//    }
//
//    @Test
//    public void getCoursesForTeacher_whenInvalidTeacherId_shouldThrowGlobalException() {
//        // Arrange
//        String teacherId = "invalidId";
//        when(courseRepository.findByTeacherTeacherId(teacherId)).thenReturn(null);
//
//        // Act & Assert
//        assertThrows(CustomizableException.class, () -> courseService.getCoursesForTeacher(teacherId));
//    }
//
//    @Test
//    public void addCourseForTeacher_whenValidTeacherAndCourseRequest_shouldReturnNewCourse() throws CustomizableException {
//        // Arrange
//        String teacherId = "1";
//        CourseRequest courseRequest = new CourseRequest("courseId", "courseName", "courseDescription");
//        Teacher teacher = new Teacher();
//        when(teacherRepository.findByTeacherId(teacherId)).thenReturn(teacher);
//        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        // Act
//        Course actualCourse = courseService.addCourseForTeacher(teacherId, courseRequest);
//
//        // Assert
//        assertEquals(courseRequest.getCourseId(), actualCourse.getCourseId());
//        assertEquals(courseRequest.getCourseName(), actualCourse.getCourseName());
//        assertEquals(courseRequest.getCourseDescription(), actualCourse.getCourseDescription());
//        assertEquals(teacher, actualCourse.getTeacher());
//    }
//
//    @Test
//    public void addCourseForTeacher_whenInvalidTeacherId_shouldThrowGlobalException() {
//        // Arrange
//        String teacherId = "invalidId";
//        CourseRequest courseRequest = new CourseRequest("courseId", "courseName", "courseDescription");
//        when(teacherRepository.findByTeacherId(teacherId)).thenReturn(null);
//
//        // Act & Assert
//        assertThrows(CustomizableException.class, () -> courseService.addCourseForTeacher(teacherId, courseRequest));
//    }
//
//    @Test(expected = CustomizableException.class)
//    public void testRemoveCourseForTeacherCourseNotFound() throws CustomizableException {
//        String teacherId = "teacherId";
//        String courseId = "courseId";
//        when(courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId)).thenReturn(null);
//        courseService.removeCourseForTeacher(teacherId, courseId);
//    }
//
//    @Test
//    public void testRemoveCourseForTeacherSuccess() throws CustomizableException {
//        String teacherId = "teacherId";
//        String courseId = "courseId";
//        Course courseToDelete = new Course();
//        when(courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId)).thenReturn(courseToDelete);
//        courseService.removeCourseForTeacher(teacherId, courseId);
//        verify(courseRepository, times(1)).delete(courseToDelete);
//    }
//
//    @Test(expected = CustomizableException.class)
//    public void testRemoveCoursesForTeacherCoursesNotFound() throws CustomizableException {
//        String teacherId = "teacherId";
//        List<String> courseIds = new ArrayList<>(Arrays.asList("course1", "course2"));
//        when(courseRepository.findByTeacherTeacherIdAndCourseIdIn(teacherId, courseIds)).thenReturn(new ArrayList<Course>());
//        courseService.removeCoursesForTeacher(teacherId, courseIds);
//    }
//
//    @Test
//    public void testRemoveCoursesForTeacherSuccess() throws CustomizableException {
//        String teacherId = "teacherId";
//        List<String> courseIds = new ArrayList<>(Arrays.asList("course1", "course2"));
//        Course course1 = new Course();
//        Course course2 = new Course();
//        List<Course> coursesToDelete = new ArrayList<>(Arrays.asList(course1, course2));
//        when(courseRepository.findByTeacherTeacherIdAndCourseIdIn(teacherId, courseIds)).thenReturn(coursesToDelete);
//        courseService.removeCoursesForTeacher(teacherId, courseIds);
//        verify(courseRepository, times(1)).deleteAll(coursesToDelete);
//    }
//}
//
