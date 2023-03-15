package com.GRP3.BPA.repository.teacherCourse;

import com.GRP3.BPA.model.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Long>, TeacherCourseCustomRepository {

}

