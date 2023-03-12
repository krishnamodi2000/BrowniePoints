package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.model.TeacherCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TeacherRepositoryImpl implements TeacherCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * @param teacherId
     * @return
     */
    @Override
    public Teacher findById(String teacherId) {
        String query = "SELECT t FROM Teacher t WHERE t.teacher_id = :teacherId";
        return entityManager.createQuery(query, Teacher.class)
                .setParameter("teacherId", teacherId)
                .getSingleResult();
    }
}
