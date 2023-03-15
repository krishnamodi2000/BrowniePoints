package com.GRP3.BPA.repository.teacherCourse;

import com.GRP3.BPA.model.TeacherCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class TeacherCourseRepositoryImpl implements TeacherCourseCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * @param teacherId
     * @return
     */

    @Override
    public List<TeacherCourse> findByTeacherId(String teacherId) {
        String query = "SELECT tc FROM TeacherCourse tc WHERE tc.teacher.teacher_id = :teacherId";
        return entityManager.createQuery(query, TeacherCourse.class)
                .setParameter("teacherId", teacherId)
                .getResultList();
    }

    /**
     * @param teacherId
     * @param courseIds
     * @return
     */
    @Override
    public List<TeacherCourse> findByTeacherIdAndCourseIdIn(String teacherId, List<String> courseIds) {
        String query = "SELECT tc FROM TeacherCourse tc WHERE tc.teacher.teacher_id = :teacherId AND tc.course.course_id IN :courseIds";
        return entityManager.createQuery(query, TeacherCourse.class)
                .setParameter("teacherId", teacherId)
                .setParameter("courseIds", courseIds)
                .getResultList();
    }

    /**
     * @param teacherId
     * @param courseId
     * @return
     */
    @Override
    public TeacherCourse findByTeacherIdAndCourseIdIn(String teacherId, String courseId) {
        String query = "SELECT tc FROM TeacherCourse tc WHERE tc.teacher.teacher_id = :teacherId AND tc.course.course_id = :courseId";
        return entityManager.createQuery(query, TeacherCourse.class)
                .setParameter("teacherId", teacherId)
                .setParameter("courseId", courseId)
                .getSingleResult();
    }
}
