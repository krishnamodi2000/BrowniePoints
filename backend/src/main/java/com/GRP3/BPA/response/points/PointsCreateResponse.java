package com.GRP3.BPA.response.points;

import com.GRP3.BPA.model.Student;
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
