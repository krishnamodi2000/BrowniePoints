package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long> {
    List<TeacherCourse> findByTeacherId(String teacherId);

    List<TeacherCourse> findByTeacherIdAndCourseIdIn(String teacherId, List<String> courseIds);
}

