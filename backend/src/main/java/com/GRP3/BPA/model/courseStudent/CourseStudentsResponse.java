package com.GRP3.BPA.model.courseStudent;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class CourseStudentsResponse {
    @Getter @Setter
    boolean success;
    @Getter @Setter
    ArrayList<StudentInfoWithName> data;
}
