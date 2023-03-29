package com.GRP3.BPA.model.course;

import com.GRP3.BPA.model.student.Student;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    @Getter
    @Setter
    private boolean status;
    @Getter
    @Setter
    private List<CourseRequest> courseRequestList;
}
