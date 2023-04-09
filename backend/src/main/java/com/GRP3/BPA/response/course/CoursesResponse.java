package com.GRP3.BPA.response.course;

import com.GRP3.BPA.request.course.CourseRequest;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoursesResponse {
    @Getter
    @Setter
    private boolean status;
    @Getter
    @Setter
    private List<CourseRequest> courseRequestList;
}
