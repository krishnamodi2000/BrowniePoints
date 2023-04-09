package com.GRP3.BPA.response.course;

import com.GRP3.BPA.request.course.CourseRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseResponseTest {
    @Test
    public void testGetSetStatus() {
        CourseResponse response = new CourseResponse();
        response.setStatus(true);
        assertTrue(response.getStatus());
        response.setStatus(false);
        assertFalse(response.getStatus());
    }

    @Test
    public void testGetSetCourseRequest() {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName("Mathematics 101");
        CourseResponse response = new CourseResponse();
        response.setCourseRequest(courseRequest);
        assertEquals(courseRequest, response.getCourseRequest());

        CourseRequest newRequest = new CourseRequest();
        newRequest.setCourseName("Computer Science 101");
        response.setCourseRequest(newRequest);
        assertEquals(newRequest, response.getCourseRequest());
    }
}