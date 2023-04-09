package com.GRP3.BPA.request.courseStudent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentRequests {
    private String courseId;
    private List<String> bannerIds;
}
