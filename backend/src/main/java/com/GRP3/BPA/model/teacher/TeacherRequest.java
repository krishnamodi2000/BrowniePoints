package com.GRP3.BPA.model.teacher;

import com.GRP3.BPA.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private User user;
    private String teacherId;
}
