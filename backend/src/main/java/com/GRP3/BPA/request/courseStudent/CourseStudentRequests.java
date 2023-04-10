package com.GRP3.BPA.request.courseStudent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Represents a request to associate multiple students with a course.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentRequests {


    /**
     * The ID of the course to associate the students with.
     */
    private String courseId;

    /**
     * The list of banner IDs of the students to associate with the course.
     */
    private List<String> bannerIds;
}
