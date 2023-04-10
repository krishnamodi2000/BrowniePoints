package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Finds a course by course id.
     * @param courseId the id of the course to find.
     * @return the course with the given course id or null if not found.
     */
    Course findByCourseId(String courseId);

    /**
     * Finds all courses with course ids in the given list.
     * @param courseIds the list of course ids to find courses for.
     * @return the list of courses with the given ids.
     */
    List<Course> findAllByCourseIdIn(List<String> courseIds);

    /**
     * Finds all courses for a given teacher id.
     * @param teacherId the id of the teacher to find courses for.
     * @return the list of courses for the given teacher id.
     */
    List<Course> findByTeacherTeacherId(String teacherId);

    /**
     * Finds all courses for a given teacher id and with course ids in the given list.
     * @param teacherId the id of the teacher to find courses for.
     * @param courseIds the list of course ids to find courses for.
     * @return the list of courses with the given course ids for the given teacher id.
     */
    List<Course> findByTeacherTeacherIdAndCourseIdIn(String teacherId, List<String> courseIds);

    /**
     * Finds a course for a given teacher id and course id.
     * @param teacherId the id of the teacher who teaches the course.
     * @param courseId the id of the course to find.
     * @return the course with the given teacher id and course id or null if not found.
     */
    Course findByTeacherTeacherIdAndCourseId(String teacherId, String courseId);
}
