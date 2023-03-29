package com.GRP3.BPA.model.course;

import com.GRP3.BPA.model.CourseRequest;
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
