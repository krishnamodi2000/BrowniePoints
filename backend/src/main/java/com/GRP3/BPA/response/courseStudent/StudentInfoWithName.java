package com.GRP3.BPA.response.courseStudent;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response object that contains information about a student enrolled in a course.
 */
public class StudentInfoWithName {

    /**
     * The banner ID of the student.
     */
    @Getter @Setter
    String bannerId;

    /**
     * The name of the student.
     */
    @Getter @Setter
    String studentName;

    /**
     * The number of points the student has earned.
     */
    @Getter @Setter
    int points;
}
