package com.GRP3.BPA.request.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class represents a request to create or update a course.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    /**
     * The ID of the course to create or update.
     */
    private String courseId;


    /**
     * The name of the course to create or update.
     */
    private String courseName;

    /**
     * The description of the course to create or update.
     */
    private String courseDescription;

}
