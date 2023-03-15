package com.GRP3.BPA.repository.course;

import com.GRP3.BPA.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> ,CourseCustomRepository{

}
