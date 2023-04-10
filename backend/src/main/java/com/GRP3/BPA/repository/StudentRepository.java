package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find a student by their Banner ID.
     *
     * @param studentId the Banner ID of the student to find
     * @return the Student object if found, or null if not found
     */
    Student findByBannerId(String studentId);


    /**
     * Find a student by their user ID.
     *
     * @param userId the user ID of the student to find
     * @return the Student object if found, or null if not found
     */
    Student findByUserId(int userId);
}