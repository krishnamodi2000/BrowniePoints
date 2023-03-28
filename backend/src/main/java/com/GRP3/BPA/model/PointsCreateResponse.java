package com.GRP3.BPA.model;

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
