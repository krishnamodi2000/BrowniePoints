package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByBannerId(String studentId);

    Student findByUserId(int userId);
}