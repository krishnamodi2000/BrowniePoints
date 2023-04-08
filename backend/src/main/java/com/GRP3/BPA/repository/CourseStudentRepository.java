package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
    List<CourseStudent> findByStudentBannerId(String studentId);

    List<CourseStudent> findByStudentBannerIdAndCourseCourseIdIn(String studentId, List<String> courseIds);
    CourseStudent findByStudentBannerIdAndCourseCourseId(String studentId, String courseId);
    List<CourseStudent> findByCourseCourseId(String courseId);
}
