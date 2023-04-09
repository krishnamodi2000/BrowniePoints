package com.GRP3.BPA.response.courseStudent;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class CourseStudentsResponse {
    @Getter @Setter
    boolean status;
    @Getter @Setter
    ArrayList<StudentInfoWithName> data;
}
