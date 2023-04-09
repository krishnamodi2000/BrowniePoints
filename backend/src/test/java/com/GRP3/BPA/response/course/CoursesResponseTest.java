package com.GRP3.BPA.response.course;

import com.GRP3.BPA.request.course.CourseRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesResponseTest {
    @Test
    public void testGetSetStatus() {
        CoursesResponse response = new CoursesResponse();
        response.setStatus(true);
        assertTrue(response.isStatus());
        response.setStatus(false);
        assertFalse(response.isStatus());
    }

    @Test
    public void testGetSetCourseRequestList() {
        List<CourseRequest> courseList = new ArrayList<>();

        CourseRequest courseRequest1 = new CourseRequest();
        courseRequest1.setCourseName("ASDC 101");
        courseList.add(courseRequest1);

        CourseRequest courseRequest2 = new CourseRequest();
        courseRequest2.setCourseName("DMWA 101");
        courseList.add(courseRequest2);

        CoursesResponse response = new CoursesResponse();
        response.setCourseRequestList(courseList);
        assertEquals(courseList, response.getCourseRequestList());

        List<CourseRequest> newCourseList = new ArrayList<>();
        CourseRequest courseRequest3 = new CourseRequest();
        courseRequest3.setCourseName("NS 101");
        newCourseList.add(courseRequest3);

        CourseRequest courseRequest4 = new CourseRequest();
        courseRequest4.setCourseName("SDC 101");
        newCourseList.add(courseRequest4);

        response.setCourseRequestList(newCourseList);
        assertEquals(newCourseList, response.getCourseRequestList());
    }
}