package com.GRP3.BPA.model.course;

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
