package com.GRP3.BPA.request.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Represents a request containing a course ID.
 */
@AllArgsConstructor
@NoArgsConstructor
public class CourseIdRequest {

    /**
     * The ID of the course.
     */
    @Getter
    @Setter
    private String courseId;
}
