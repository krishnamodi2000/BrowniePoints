package com.GRP3.BPA.repository.student;


import com.GRP3.BPA.model.Student;

public interface StudentCustomRepository {
    Student findById(String studentId);
}
