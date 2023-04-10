package com.GRP3.BPA.response.course;

import com.GRP3.BPA.request.course.CourseRequest;
import lombok.*;

import java.util.List;


/**
 * Represents a response object containing a list of course requests.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursesResponse {

    /**
     * Represents the status of the response.
     */
    @Getter
    @Setter
    private boolean status;

    /**
     * Represents the list of course requests.
     */
    @Getter
    @Setter
    private List<CourseRequest> courseRequestList;
}
