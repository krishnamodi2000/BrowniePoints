package com.GRP3.BPA.response.points;

import com.GRP3.BPA.model.Student;
import lombok.Getter;
import lombok.Setter;

/**
 * Response object for creating points for a student.
 */
public class PointsCreateResponse {

    /**
     * Whether the operation was successful or not.
     */
    @Getter
    @Setter
    private boolean success;


    /**
     * The updated student object after points were added.
     */
    @Getter
    @Setter
    private Student student;
}
