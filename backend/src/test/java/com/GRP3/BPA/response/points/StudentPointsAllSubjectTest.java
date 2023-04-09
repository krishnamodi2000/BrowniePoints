package com.GRP3.BPA.response.points;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentPointsAllSubjectTest {
    @Test
    public void testGetSetCourseId() {
        StudentPointsAllSubject studentPoints = new StudentPointsAllSubject();
        studentPoints.setCourseId("CS101");
        assertEquals("CS101", studentPoints.getCourseId());
        studentPoints.setCourseId("MATH101");
        assertEquals("MATH101", studentPoints.getCourseId());
    }

    @Test
    public void testGetSetCourseName() {
        StudentPointsAllSubject studentPoints = new StudentPointsAllSubject();
        studentPoints.setCourseName("Computer Science 101");
        assertEquals("Computer Science 101", studentPoints.getCourseName());
        studentPoints.setCourseName("Mathematics 101");
        assertEquals("Mathematics 101", studentPoints.getCourseName());
    }

    @Test
    public void testGetSetPoints() {
        StudentPointsAllSubject studentPoints = new StudentPointsAllSubject();
        studentPoints.setPoints(90);
        assertEquals(90, studentPoints.getPoints());
        studentPoints.setPoints(80);
        assertEquals(80, studentPoints.getPoints());
    }
}