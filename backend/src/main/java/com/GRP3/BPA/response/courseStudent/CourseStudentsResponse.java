package com.GRP3.BPA.response.courseStudent;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Represents a response object containing a list of students enrolled in a course.
 */
public class CourseStudentsResponse {

    /**
     * The status of the response.
     */
    @Getter @Setter
    boolean status;


    /**
     * The list of students enrolled in the course.
     */
    @Getter @Setter
    ArrayList<StudentInfoWithName> data;
}
