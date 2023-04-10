package com.GRP3.BPA.response.course;

import com.GRP3.BPA.request.course.CourseRequest;
import lombok.*;

/**
 * A class representing the response returned when a course is created or updated.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    /**
     * A boolean indicating the status of the operation.
     */
    @Getter
    @Setter
    private boolean status;

    /**
     * A {@link com.GRP3.BPA.request.course.CourseRequest} object representing the course that was created or updated.
     */
    @Getter
    @Setter
    private CourseRequest courseRequest;


    /**
     * Returns the status of the operation.
     *
     * @return a boolean indicating the status of the operation.
     */
    public boolean getStatus() {
        return status;
    }
}
