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
    private String bannerId;
    private String courseId;

}
