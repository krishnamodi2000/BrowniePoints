package com.GRP3.BPA.request.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A request object used to represent a list of course IDs.
 */
@AllArgsConstructor
@NoArgsConstructor
public class CourseIdsRequest {

    /** The list of course IDs. */
    @Getter @Setter
    private List<String> courseIds;
}
