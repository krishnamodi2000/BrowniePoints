package com.GRP3.BPA.request.course;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CourseRequestTest {

    @Test
    public void testGetCourseId() {
        CourseRequest request = new CourseRequest("C01", "Java Programming", "Learn Java programming basics");
        assertEquals("C01", request.getCourseId());
    }

    @Test
    public void testGetCourseName() {
        CourseRequest request = new CourseRequest("C01", "Java Programming", "Learn Java programming basics");
        assertEquals("Java Programming", request.getCourseName());
    }

    @Test
    public void testGetCourseDescription() {
        CourseRequest request = new CourseRequest("C01", "Java Programming", "Learn Java programming basics");
        assertEquals("Learn Java programming basics", request.getCourseDescription());
    }

    @Test
    public void testSetCourseId() {
        CourseRequest request = new CourseRequest();
        request.setCourseId("C01");
        assertEquals("C01", request.getCourseId());
    }

    @Test
    public void testSetCourseName() {
        CourseRequest request = new CourseRequest();
        request.setCourseName("Java Programming");
        assertEquals("Java Programming", request.getCourseName());
    }

    @Test
    public void testSetCourseDescription() {
        CourseRequest request = new CourseRequest();
        request.setCourseDescription("Learn Java programming basics");
        assertEquals("Learn Java programming basics", request.getCourseDescription());
    }

}