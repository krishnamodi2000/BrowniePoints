package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {

    /**
     * Retrieves all course student entities associated with a given student ID.
     * @param studentId the ID of the student to retrieve course student entities for.
     * @return a list of course student entities associated with the given student ID.
     */
    List<CourseStudent> findByStudentBannerId(String studentId);

    /**
     * Retrieves all course student entities associated with a given student ID and a list of course IDs.
     * @param studentId the ID of the student to retrieve course student entities for.
     * @param courseIds the list of course IDs to retrieve course student entities for.
     * @return a list of course student entities associated with the given student ID and course IDs.
     */
    List<CourseStudent> findByStudentBannerIdAndCourseCourseIdIn(String studentId, List<String> courseIds);

    /**
     * Retrieves a course student entity associated with a given student ID and a course ID.
     * @param studentId the ID of the student to retrieve the course student entity for.
     * @param courseId the ID of the course to retrieve the course student entity for.
     * @return the course student entity associated with the given student ID and course ID.
     */
    CourseStudent findByStudentBannerIdAndCourseCourseId(String studentId, String courseId);

    /**
     * Retrieves all course student entities associated with a given course ID.
     * @param courseId the ID of the course to retrieve course student entities for.
     * @return a list of course student entities associated with the given course ID.
     */
    List<CourseStudent> findByCourseCourseId(String courseId);
}
