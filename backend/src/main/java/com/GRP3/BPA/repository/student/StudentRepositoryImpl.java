package com.GRP3.BPA.repository.student;

import com.GRP3.BPA.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class StudentRepositoryImpl implements StudentCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * @param studentId
     * @return
     */
    @Override
    public Student findById(String studentId) {
        String query = "SELECT s FROM Student s WHERE s.banner_id= :studentId";
        return entityManager.createQuery(query, Student.class)
                .setParameter("studentId", studentId)
                .getSingleResult();
    }
}
