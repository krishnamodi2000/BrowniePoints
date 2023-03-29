package com.GRP3.BPA.model.course;

import com.GRP3.BPA.model.CourseRequest;
import lombok.*;

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
    private CourseRequest courseRequest;


}
