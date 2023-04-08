package com.GRP3.BPA.response.courseStudent;

import lombok.Getter;
import lombok.Setter;

public class CourseStudentResponse {

    @Getter
    @Setter
    boolean status;
    @Getter @Setter
    StudentInfoWithName data;
}
