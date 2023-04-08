package com.GRP3.BPA.response.courseStudent;

import lombok.Getter;
import lombok.Setter;

public class StudentInfoWithName {
    @Getter @Setter
    String bannerId;
    @Getter @Setter
    String studentName;
    @Getter @Setter
    int points;
}
