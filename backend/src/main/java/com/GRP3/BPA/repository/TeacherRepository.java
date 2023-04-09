package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByTeacherId(String teacherId);
    Teacher findByUserId(int userId);
}
