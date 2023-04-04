//package com.GRP3.BPA.repository.courseStudent;
//
//import com.GRP3.BPA.model.CourseStudent.CourseStudent;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//import java.util.List;
//
//public class CourseStudentRepositoryImpl implements CourseStudentCustomRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//    /**
//     * @param studentId
//     * @return
//     */
//    @Override
//    public List<CourseStudent> findByStudentId(String studentId) {
//        String query = "SELECT cs FROM CourseStudent cs WHERE cs.student.banner_id = :studentId";
//        return entityManager.createQuery(query, CourseStudent.class)
//                .setParameter("studentId", studentId)
//                .getResultList();
//    }
//
//    /**
//     * @param studentId
//     * @param courseIds
//     * @return
//     */
//    @Override
//    public List<CourseStudent> findByStudentIdAndCourseIdIn(String studentId, List<String> courseIds) {
//        String query = "SELECT cs FROM CourseStudent cs WHERE cs.student.banner_id = :studentId AND cs.course.course_id IN :courseIds";
//        return entityManager.createQuery(query, CourseStudent.class)
//                .setParameter("studentId", studentId)
//                .setParameter("courseIds", courseIds)
//                .getResultList();
//    }
//
//    /**
//     * @param studentId
//     * @param courseId
//     * @return
//     */
//    @Override
//    public CourseStudent findByStudentIdAndCourseIdIn(String studentId, String courseId) {
//        String query = "SELECT cs FROM CourseStudent cs WHERE cs.student.banner_id = :studentId AND cs.course.course_id = :courseId";
//        return entityManager.createQuery(query, CourseStudent.class)
//                .setParameter("studentId", studentId)
//                .setParameter("courseId", courseId)
//                .getSingleResult();
//    }
//}
