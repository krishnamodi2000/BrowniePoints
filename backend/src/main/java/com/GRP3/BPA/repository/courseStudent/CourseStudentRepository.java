package com.GRP3.BPA.repository.courseStudent;

import com.GRP3.BPA.model.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long>, CourseStudentCustomRepository {
}
