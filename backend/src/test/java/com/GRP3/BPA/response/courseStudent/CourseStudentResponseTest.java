package com.GRP3.BPA.response.courseStudent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class CourseStudentResponseTest {

    @Test
    public void testGetSetStatus() {
        CourseStudentResponse response = new CourseStudentResponse();
        response.setStatus(true);
        assertTrue(response.isStatus());
        response.setStatus(false);
        assertFalse(response.isStatus());
    }

    @Test
    public void testGetSetData() {
        CourseStudentResponse response = new CourseStudentResponse();
        StudentInfoWithName student = new StudentInfoWithName();
        student.setStudentName("John");
        response.setData(student);
        assertEquals(student, response.getData());
    }
}
