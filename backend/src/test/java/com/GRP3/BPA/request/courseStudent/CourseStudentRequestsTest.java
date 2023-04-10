package com.GRP3.BPA.request.courseStudent;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class CourseStudentRequestsTest {

    @Test
    public void testGetterAndSetter() {
        CourseStudentRequests csr = new CourseStudentRequests();
        csr.setCourseId("CS101");
        csr.setBannerIds(Arrays.asList("B001", "B002", "B003"));

        assertEquals("CS101", csr.getCourseId());
        assertEquals(Arrays.asList("B001", "B002", "B003"), csr.getBannerIds());
    }

    @Test
    public void testBuilder() {
        CourseStudentRequests csr = CourseStudentRequests.builder()
                .courseId("CS101")
                .bannerIds(Arrays.asList("B001", "B002", "B003"))
                .build();

        assertEquals("CS101", csr.getCourseId());
        assertEquals(Arrays.asList("B001", "B002", "B003"), csr.getBannerIds());
    }

    @Test
    public void testNoArgsConstructor() {
        CourseStudentRequests csr = new CourseStudentRequests();

        assertNull(csr.getCourseId());
        assertNull(csr.getBannerIds());
    }

    @Test
    public void testAllArgsConstructor() {
        CourseStudentRequests csr = new CourseStudentRequests("CS101", Arrays.asList("B001", "B002", "B003"));

        assertEquals("CS101", csr.getCourseId());
        assertEquals(Arrays.asList("B001", "B002", "B003"), csr.getBannerIds());
    }

}