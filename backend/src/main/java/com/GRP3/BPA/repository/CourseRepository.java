package com.GRP3.BPA.repository;

import com.GRP3.BPA.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> ,CourseCustomRepository{

}
