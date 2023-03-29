package com.GRP3.BPA.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String courseId;
    private String courseName;
    private String courseDescription;
    private String teacherId;
}
