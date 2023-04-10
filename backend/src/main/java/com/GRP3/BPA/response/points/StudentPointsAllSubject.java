package com.GRP3.BPA.response.points;

import lombok.Getter;
import lombok.Setter;

/**
 * A class representing a student's points in a particular subject
 */
public class StudentPointsAllSubject {
    @Getter @Setter
    String courseId;
    @Getter @Setter
    String courseName;
    @Getter @Setter
    int points;
}
