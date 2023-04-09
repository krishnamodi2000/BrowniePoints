package com.GRP3.BPA.request.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CourseIdsRequest {
    @Getter @Setter
    private List<String> courseIds;
}
