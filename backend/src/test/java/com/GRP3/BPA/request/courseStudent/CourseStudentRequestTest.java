package com.GRP3.BPA.request.courseStudent;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseStudentRequestTest {

    @Test
    public void testGettersAndSetters() {
        CourseStudentRequest request = new CourseStudentRequest();
        request.setBannerId("12345");
        request.setCourseId("CS101");
        assertEquals("12345", request.getBannerId());
        assertEquals("CS101", request.getCourseId());
    }

    @Test
    public void testConstructor() {
        CourseStudentRequest request = new CourseStudentRequest("12345", "CS101");
        assertEquals("12345", request.getBannerId());
        assertEquals("CS101", request.getCourseId());
    }

    @Test
    public void testBuilder() {
        CourseStudentRequest request = CourseStudentRequest.builder()
                .bannerId("12345")
                .courseId("CS101")
                .build();
        assertEquals("12345", request.getBannerId());
        assertEquals("CS101", request.getCourseId());
    }
}