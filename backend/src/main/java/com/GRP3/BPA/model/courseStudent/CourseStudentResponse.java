package com.GRP3.BPA.model.courseStudent;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class CourseStudentResponse {

    @Getter
    @Setter
    boolean status;
    @Getter @Setter
    StudentInfoWithName data;
}
