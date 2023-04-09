package com.GRP3.BPA.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void testCourseCreation() {
        Teacher teacher = new Teacher();
        Course course = new Course("C001", "Mathematics", "Introduction to Calculus", teacher);
        assertEquals("C001", course.getCourseId());
        assertEquals("Mathematics", course.getCourseName());
        assertEquals("Introduction to Calculus", course.getCourseDescription());
        assertEquals(teacher, course.getTeacher());
    }

    @Test
    public void testCourseCreationWithNullCourseId() {
        Teacher teacher = new Teacher();
        try {
            String courseId = null;
            new Course(courseId, "Mathematics", "Introduction to Calculus", teacher);
        } catch (NullPointerException e) {
            assertEquals("courseId cannot be null", e.getMessage());
        }
    }

    @Test
    public void testCourseCreationWithEmptyCourseId() {
        Teacher teacher = new Teacher();
        try {
            new Course("", "Mathematics", "Introduction to Calculus", teacher);
        } catch (IllegalArgumentException e) {
            assertEquals("courseId cannot be empty", e.getMessage());
        }
    }


    @Test
    public void testCourseCreationWithNullCourseName() {
        Teacher teacher = new Teacher();
        try {
            new Course("C001", null, "Introduction to Calculus", teacher);
        } catch (NullPointerException e) {
            assertEquals("courseName cannot be null", e.getMessage());
        }
    }

    @Test
    public void testCourseCreationWithNullCourseDescription() {
        Teacher teacher = new Teacher();
        Course course = new Course("C001", "Mathematics", null, teacher);
        assertNull(course.getCourseDescription());
    }

    @Test
    public void testCourseCreationWithEmptyCourseDescription() {
        Teacher teacher = new Teacher();
        Course course = new Course("C001", "Mathematics", "", teacher);
        assertEquals("", course.getCourseDescription());
    }

    @Test
    public void testSetTeacher() {
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();
        Course course = new Course("C001", "Mathematics", "Introduction to Calculus", teacher1);
        course.setTeacher(teacher2);
        assertEquals(teacher2, course.getTeacher());
    }

    @Test
    public void testSetNullTeacher() {
        Course course = new Course("C001", "Mathematics", "Introduction to Calculus", new Teacher());
        course.setTeacher(null);
        assertNull(course.getTeacher());
    }
}