package com.GRP3.BPA.response.course;

import com.GRP3.BPA.request.course.CourseRequest;
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


    public boolean getStatus() {
        return status;
    }
}
