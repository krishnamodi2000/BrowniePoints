package com.GRP3.BPA.model.courseStudent;

import com.GRP3.BPA.model.student.Student;
import lombok.Getter;
import lombok.Setter;

public class PointsCreateResponse {
    @Getter
    @Setter
    private boolean success;
    @Getter
    @Setter
    private Student student;
}
