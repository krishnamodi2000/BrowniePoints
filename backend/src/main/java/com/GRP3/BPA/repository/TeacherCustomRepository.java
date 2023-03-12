package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Teacher;

public interface TeacherCustomRepository {
    Teacher findById(String teacherId);
}
