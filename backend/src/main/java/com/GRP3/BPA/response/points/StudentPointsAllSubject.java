package com.GRP3.BPA.response.points;

import lombok.Getter;
import lombok.Setter;

public class StudentPointsAllSubject {
    @Getter @Setter
    String courseId;
    @Getter @Setter
    String courseName;
    @Getter @Setter
    int points;
}
