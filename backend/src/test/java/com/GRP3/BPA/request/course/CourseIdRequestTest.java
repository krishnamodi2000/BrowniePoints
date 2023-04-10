package com.GRP3.BPA.request.course;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CourseIdRequestTest {

    @Test
    public void testGetCourseId() {
        CourseIdRequest request = new CourseIdRequest("C001");
        assertEquals("C001", request.getCourseId());
    }

    @Test
    public void testSetCourseId() {
        CourseIdRequest request = new CourseIdRequest();
        request.setCourseId("C002");
        assertEquals("C002", request.getCourseId());
    }

}