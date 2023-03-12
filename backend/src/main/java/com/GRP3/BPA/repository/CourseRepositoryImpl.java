package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.model.TeacherCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CourseRepositoryImpl implements CourseCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * @param courseId
     * @return
     */
    @Override
    public Course findById(String courseId) {
//        String query = "SELECT c FROM Course c WHERE c.id = :courseId";
        String query = "SELECT c FROM Course c WHERE c.course_id = :courseId";
        return entityManager.createQuery(query, Course.class)
                .setParameter("courseId", courseId)
                .getSingleResult();

    }

    /**
     * @param courseIds
     * @return
     */
    @Override
    public List<Course> findAllById(List<String> courseIds) {
        String query = "SELECT c FROM Course c WHERE c.course_id IN :courseIds";
        return entityManager.createQuery(query, Course.class)
                .setParameter("courseIds", courseIds)
                .getResultList();
    }
}
