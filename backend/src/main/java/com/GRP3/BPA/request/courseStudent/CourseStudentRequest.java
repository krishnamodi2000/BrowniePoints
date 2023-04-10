package com.GRP3.BPA.request.courseStudent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentRequest {

    /**
     * The banner ID of the student.
     */
    private String bannerId;

    /**
     * The ID of the course.
     */
    private String courseId;

}
