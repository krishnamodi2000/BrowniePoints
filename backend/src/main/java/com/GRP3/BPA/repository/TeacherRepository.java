package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface extends the JpaRepository and provides methods to access Teacher entities in the database.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * Returns the teacher entity with the given teacher ID.
     *
     * @param teacherId the ID of the teacher to retrieve
     * @return the teacher entity with the given teacher ID
     */
    Teacher findByTeacherId(String teacherId);

    /**
     * Returns the teacher entity associated with the given user ID.
     *
     * @param userId the ID of the user associated with the teacher
     * @return the teacher entity associated with the given user ID
     */
    Teacher findByUserId(int userId);
}
