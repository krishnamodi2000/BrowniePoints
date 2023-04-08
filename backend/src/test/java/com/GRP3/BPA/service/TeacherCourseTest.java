//package com.GRP3.BPA.service;
//
//import com.GRP3.BPA.model.Course;
//import com.GRP3.BPA.model.Teacher;
//import com.GRP3.BPA.model.TeacherCourse;
//import com.GRP3.BPA.repository.CourseRepository;
//import com.GRP3.BPA.repository.teacherCourse.TeacherCourseRepository;
//import com.GRP3.BPA.repository.TeacherRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class TeacherCourseTest {
//    @Mock
//    private TeacherCourseRepository teacherCourseRepository;
//    @Mock
//    private TeacherRepository teacherRepository;
//    @Mock
//    private CourseRepository courseRepository;
//
//    private TeacherCourseService teacherCourseService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        teacherCourseService = new TeacherCourseService(teacherCourseRepository, teacherRepository, courseRepository);
//    }
//
//    @Test
//    public void testGetCoursesForTeacher() {
//        TeacherCourse teacherCourse1 = new TeacherCourse();
//        teacherCourse1.setId(1L);
//        TeacherCourse teacherCourse2 = new TeacherCourse();
//        teacherCourse2.setId(2L);
//        when(teacherCourseRepository.findByTeacherId("teacher123")).thenReturn(Arrays.asList(teacherCourse1, teacherCourse2));
//
//        List<TeacherCourse> teacherCourses = teacherCourseService.getCoursesForTeacher("teacher123");
//
//        assertEquals(2, teacherCourses.size());
//    }
//
//    @Test
//    public void testAddCourseForTeacher() {
//        Teacher teacher = new Teacher();
//        teacher.setTeacher_id("teacher123");
//        when(teacherRepository.findById("teacher123")).thenReturn(teacher);
//        Course course = new Course();
//        course.setCourse_id("course123");
//        when(courseRepository.findById("course123")).thenReturn(course);
//        teacherCourseService.addCourseForTeacher("teacher123", "course123");
//        verify(teacherCourseRepository, times(1)).save(any(TeacherCourse.class));
//    }
//
//    @Test
//    public void testAddCoursesForTeacher() {
//        Teacher teacher = new Teacher();
//        teacher.setTeacher_id("teacher123");
//        when(teacherRepository.findById("teacher123")).thenReturn(teacher);
//        Course course1 = new Course();
//        course1.setCourse_id("course123");
//        Course course2 = new Course();
//        course2.setCourse_id("course456");
//        when(courseRepository.findAllById(Arrays.asList("course123", "course456"))).thenReturn(Arrays.asList(course1, course2));
//
//        List<TeacherCourse> teacherCourses = teacherCourseService.addCoursesForTeacher("teacher123", Arrays.asList("course123", "course456"));
//
//        assertEquals(2, teacherCourses.size());
//        verify(teacherCourseRepository, times(1)).saveAll(anyList());
//    }
//
//    @Test
//    public void testRemoveCourseForTeacher() {
//        TeacherCourse teacherCourse = new TeacherCourse();
//        teacherCourse.setId(1L);
//        when(teacherCourseRepository.findByTeacherIdAndCourseIdIn("teacher123", "course123")).thenReturn(teacherCourse);
//        teacherCourseService.removeCourseForTeacher("teacher123", "course123");
//        verify(teacherCourseRepository, times(1)).delete(teacherCourse);
//    }
//
//    @Test
//    public void testRemoveCoursesForTeacher() {
//        TeacherCourse teacherCourse1 = new TeacherCourse();
//        teacherCourse1.setId(1L);
//        TeacherCourse teacherCourse2 = new TeacherCourse();
//        teacherCourse2.setId(2L);
//        when(teacherCourseRepository.findByTeacherIdAndCourseIdIn("teacher123", Arrays.asList("course123", "course456")))
//                .thenReturn(Arrays.asList(teacherCourse1, teacherCourse2));
//
//        teacherCourseService.removeCoursesForTeacher("teacher123", Arrays.asList("course123", "course456"));
//
//        verify(teacherCourseRepository, times(1)).deleteAll(Arrays.asList(teacherCourse1, teacherCourse2));
//    }
//
//}
