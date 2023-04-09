package com.GRP3.BPA.request.course;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseIdsRequestTest {

    @Test
    public void testGetCourseIds() {
        CourseIdsRequest request = new CourseIdsRequest();
        request.setCourseIds(List.of("course1", "course2"));
        assertEquals(List.of("course1", "course2"), request.getCourseIds());
    }

    @Test
    public void testSetCourseIds() {
        CourseIdsRequest request = new CourseIdsRequest();
        request.setCourseIds(List.of("course3", "course4"));
        assertEquals(List.of("course3", "course4"), request.getCourseIds());
    }

    @Test
    public void testEmptyCourseIds() {
        CourseIdsRequest request = new CourseIdsRequest();
        List<String> courseIds = request.getCourseIds();
        assertTrue(courseIds == null || courseIds.isEmpty());
    }

    @Test
    public void testNullCourseIds() {
        CourseIdsRequest request = new CourseIdsRequest();
        request.setCourseIds(null);
        assertNull(request.getCourseIds());
    }
}
