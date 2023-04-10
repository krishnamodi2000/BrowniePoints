package com.GRP3.BPA.response.courseStudent;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the response for a course student operation.
 */
public class CourseStudentResponse {

    /**
     * The status of the operation.
     */
    @Getter
    @Setter
    boolean status;

    /**
     * The data related to the course student operation.
     */
    @Getter @Setter
    StudentInfoWithName data;
}
