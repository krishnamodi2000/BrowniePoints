package com.GRP3.BPA.response.courseStudent;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseStudentsResponseTest {
    @Test
    public void testGetSetStatus() {
        CourseStudentsResponse response = new CourseStudentsResponse();
        response.setStatus(true);
        assertTrue(response.isStatus());
        response.setStatus(false);
        assertFalse(response.isStatus());
    }

    @Test
    public void testGetSetData() {
        CourseStudentsResponse response = new CourseStudentsResponse();
        ArrayList<StudentInfoWithName> students = new ArrayList<StudentInfoWithName>();
        StudentInfoWithName student1 = new StudentInfoWithName();
        student1.setStudentName("xyz");
        student1.setBannerId("B006969");
        student1.setPoints(1);
        StudentInfoWithName student2 = new StudentInfoWithName();
        student2.setStudentName("B006967");
        student2.setPoints(2);
        students.add(student1);
        students.add(student2);
        response.setData(students);
        assertEquals(students, response.getData());
    }

    @Test
    public void testGetDataSize() {
        CourseStudentsResponse response = new CourseStudentsResponse();
        ArrayList<StudentInfoWithName> students = new ArrayList<StudentInfoWithName>();
        StudentInfoWithName student1 = new StudentInfoWithName();
        student1.setStudentName("xyz");
        student1.setBannerId("B006969");
        student1.setPoints(1);
        StudentInfoWithName student2 = new StudentInfoWithName();
        student2.setStudentName("B006967");
        student2.setPoints(2);
        students.add(student1);
        students.add(student2);
        response.setData(students);
        assertEquals(students.size(), response.getData().size());
    }

    @Test
    public void testGetDataElement() {
        CourseStudentsResponse response = new CourseStudentsResponse();
        ArrayList<StudentInfoWithName> students = new ArrayList<StudentInfoWithName>();
        StudentInfoWithName student1 = new StudentInfoWithName();
        student1.setStudentName("xyz");
        student1.setBannerId("B006969");
        student1.setPoints(1);
        StudentInfoWithName student2 = new StudentInfoWithName();
        student2.setStudentName("B006967");
        student2.setPoints(2);
        students.add(student1);
        students.add(student2);
        response.setData(students);
        assertEquals(students.get(0), response.getData().get(0));
        assertEquals(students.get(1), response.getData().get(1));
    }
}