package com.GRP3.BPA.model;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CourseStudentTest {

    @Test
    public void testCreateCourseStudent() {
        Student student = new Student();
        student.setBannerId("123456789");
        Course course = new Course();
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent.setPoints(90);
        assertEquals(student, courseStudent.getStudent());
        assertEquals(course, courseStudent.getCourse());
        assertEquals(90, courseStudent.getPoints().intValue());
    }

    @Test
    public void testCreateCourseStudentWithNullStudent() {
        Course course = new Course();
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(null);
        courseStudent.setCourse(course);
        courseStudent.setPoints(90);
    }

    @Test
    public void testCreateCourseStudentWithNullCourse() {

        Student student = new Student();
        student.setBannerId("123456789");
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(null);
        courseStudent.setPoints(90);
    }

    @Test
    public void testCreateCourseStudentWithNullPoints() {
        Student student = new Student();
        student.setBannerId("123456789");
        Course course = new Course();
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent.setPoints(null);
        assertNull(courseStudent.getPoints());
    }

    @Test
    public void testCourseStudentEquals() {
        Student student = new Student();
        student.setBannerId("123456789");
        Course course = new Course();
    }
}

