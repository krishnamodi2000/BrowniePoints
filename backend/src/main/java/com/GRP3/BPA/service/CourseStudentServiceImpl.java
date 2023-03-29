package com.GRP3.BPA.service;

import com.GRP3.BPA.model.*;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.courseStudent.CourseStudentRepository;
import com.GRP3.BPA.repository.student.StudentRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseStudentServiceImpl implements CourseStudentService {
    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final CourseStudentRepository courseStudentRepository;

    public CourseStudentServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository, CourseStudentRepository courseStudentRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    /**
     * @param courseStudentRequest
     * @return
     */
    @Override
    public CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) {
        Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseStudentRequest.getCourseId());
        if (course == null) {
            throw new RuntimeException("Teacher with ID" + teacherId + "taking course with with courseID " + courseStudentRequest.getCourseId() + " not found.");
        }
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(courseStudentRequest.getStudentId(), courseStudentRequest.getCourseId());
        if (courseStudent != null) {
            throw new RuntimeException("Student with ID" + courseStudentRequest.getStudentId() + "taking course with with courseID " + courseStudentRequest.getCourseId() + " already enrolled in it.");
        }
        Student student = studentRepository.findByBannerId(courseStudentRequest.getStudentId());
        CourseStudent courseStudentToAdd = new CourseStudent();
        courseStudentToAdd.setCourse(course);
        courseStudentToAdd.setStudent(student);

        return courseStudentRepository.save(courseStudentToAdd);
    }

    /**
     * @param courseStudentRequest
     */
    @Override
    public void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) {
        Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseStudentRequest.getCourseId());
        if (course == null) {
            throw new RuntimeException("Teacher with ID" + teacherId + "taking course with with courseID " + courseStudentRequest.getCourseId() + " not found.");
        }
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(courseStudentRequest.getStudentId(), courseStudentRequest.getCourseId());
        if (courseStudent == null) {
            throw new RuntimeException("Student with ID" + courseStudentRequest.getStudentId() + "taking course with with courseID " + courseStudentRequest.getCourseId() + " is not enrolled in it.");
        }
        courseStudentRepository.delete(courseStudent);
    }

}
