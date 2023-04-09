package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.*;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;
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
public class CourseStudentServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseStudentRepository courseStudentRepository;

    @Mock
    private CourseRepository courseRepository;


    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private CourseStudentServiceImpl courseStudentService;
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
    public void testGetStudentsForACourse() throws CustomizableException {
        courseStudents.add(courseStudent1);
        courseStudents.add(courseStudent2);
        // Mock the behavior of the repositories
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        when(courseStudentRepository.findByCourseCourseId("1")).thenReturn(courseStudents);

        List<CourseStudent> result = courseStudentService.getStudentsForACourse("1","1");

        Assertions.assertNotNull(result);

        verify(courseStudentRepository, times(1)).findByCourseCourseId("1");
    }

    @Test
    public void testGetStudentsForACourseIfTeacherDoesNotExists(){

        // Call the service method and expect an exception to be thrown
        Assertions.assertThrows(CustomizableException.class, () -> courseStudentService.getStudentsForACourse("1","1"),
                "The method should throw a CustomizableException when the teacher doesn't take any courses.");

        // Verify that the repository method was called once with the correct argument
        verify(courseStudentRepository,never()).findByCourseCourseId("1");
    }

    @Test
    public void testAddStudentToCourseForTeacher() throws CustomizableException{
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        when(studentRepository.findByBannerId("1")).thenReturn(student);

        when(courseStudentRepository.save(any(CourseStudent.class))).thenReturn(courseStudent1);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);

        CourseStudent result = courseStudentService.addStudent("1", courseStudentRequest);

        Assertions.assertEquals(courseStudent1,result);

        verify(courseStudentRepository,times(1)).findByStudentBannerIdAndCourseCourseId("1","1");
    }

    @Test
    public void testAddStudentToCourseForTeacherIfTeacherDoesNotExists(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudent("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");
    }

    @Test
    public void testAddStudentToCourseForTeacherIfCourseDoesNotExists(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudent("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");

    }

    @Test
    public void testAddStudentToCourseForTeacherIfStudentDoesNotExists(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudent("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");

    }

    @Test
    public void testAddStudentToCourseForTeacherIfStudentEnrolledInCourse(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudent("1", courseStudentRequest);
        });

    }

    @Test
    public void testRemoveStudentFromCourseForTeacher() throws CustomizableException{

        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        when(studentRepository.findByBannerId("1")).thenReturn(student);
        when(courseStudentRepository.findByStudentBannerIdAndCourseCourseId("1", "1")).thenReturn(courseStudent1);

        courseStudentService.removeStudent("1", courseStudentRequest);

        Assertions.assertNull(courseRepository.findByCourseId("1"));
        verify(courseStudentRepository, times(1)).delete(courseStudent1);
    }

    @Test
    public void testRemoveStudentFromCourseForTeacherIfTeacherDoesNotExists(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudent("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");
    }
    @Test
    public void testRemoveStudentFromCourseForTeacherIfCourseDoesNotExists(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudent("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");

    }
    @Test
    public void testRemoveStudentFromCourseForTeacherIfStudentDoesNotExists(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudent("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");

    }

    @Test
    public void testRemoveStudentFromCourseForTeacherIfStudentEnrolledInCourse(){
        CourseStudentRequest courseStudentRequest = new CourseStudentRequest();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerId("1");

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudent("1", courseStudentRequest);
        });

    }

    @Test
    public void testAddStudentsToCourseForTeacher() throws CustomizableException{
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        List<CourseStudent> courseStudentList=new ArrayList<>();
        courseStudentList.add(courseStudent1);
        courseStudentList.add(courseStudent2);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        when(studentRepository.findByBannerId("1")).thenReturn(student);
        when(studentRepository.findByBannerId("2")).thenReturn(student1);
        when(courseStudentRepository.save(any(CourseStudent.class))).thenReturn(courseStudent1,courseStudent2);
        List<CourseStudent> result = courseStudentService.addStudents("1", courseStudentRequest);

        Assertions.assertNotNull(result);
    }

    @Test
    public void testAddStudentsToCourseForTeacherIfTeacherDoesNotExists(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudents("1", courseStudentRequest);
        });

    }

    @Test
    public void testAddStudentsToCourseForTeacherIfCourseDoesNotExists(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudents("1", courseStudentRequest);
        });


    }
    @Test
    public void testAddStudentsToCourseForTeacherIfStudentDoesNotExists(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudents("1", courseStudentRequest);
        });

    }

    @Test
    public void testAddStudentsToCourseForTeacherIfStudentEnrolledInCourse(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.addStudents("1", courseStudentRequest);
        });

    }

    @Test
    public void testRemoveStudentsFromCourseForTeacher() throws CustomizableException{

        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);

        List<CourseStudent> courseStudentList=new ArrayList<>();
        courseStudentList.add(courseStudent1);
        courseStudentList.add(courseStudent2);

        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        when(studentRepository.findByBannerId("1")).thenReturn(student);
        when(studentRepository.findByBannerId("2")).thenReturn(student);
        when(courseStudentRepository.findByStudentBannerIdAndCourseCourseId("1", "1")).thenReturn(courseStudent1);
        when(courseStudentRepository.findByStudentBannerIdAndCourseCourseId("2", "1")).thenReturn(courseStudent2);
        courseStudentService.removeStudents("1", courseStudentRequest);

        Assertions.assertEquals(Collections.emptyList(), courseStudentRepository.findByStudentBannerId("1"));
        Assertions.assertEquals(Collections.emptyList(), courseStudentRepository.findByStudentBannerId("2"));
    }

    @Test
    public void testRemoveStudentsFromCourseForTeacherIfTeacherDoesNotExists(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudents("1", courseStudentRequest);
        });


    }

    @Test
    public void testRemoveStudentsFromCourseForTeacherIfCourseDoesNotExists(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudents("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");

    }
    @Test
    public void testRemoveStudentsFromCourseForTeacherIfStudentDoesNotExists(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);
        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);
        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudents("1", courseStudentRequest);
        });

        verify(courseStudentRepository,never()).findByStudentBannerIdAndCourseCourseId("1","1");

    }

    @Test
    public void testRemoveStudentsFromCourseForTeacherIfStudentEnrolledInCourse(){
        List<String> bannerIds=new ArrayList<>();
        bannerIds.add("1");
        bannerIds.add("2");
        CourseStudentRequests courseStudentRequest = new CourseStudentRequests();
        courseStudentRequest.setCourseId("1");
        courseStudentRequest.setBannerIds(bannerIds);

        when(teacherRepository.findByTeacherId("1")).thenReturn(teacher);
        when(courseRepository.findByTeacherTeacherIdAndCourseId("1","1")).thenReturn(course1);

        Assertions.assertThrows(CustomizableException.class, () -> {
            courseStudentService.removeStudents("1", courseStudentRequest);
        });

    }


}
