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

        CourseStudent courseStudent=new CourseStudent();
//        CourseStudent courseStudentToAdd = checkCourseStatus(teacherId,courseStudentRequest);
//        if(courseStudentToAdd==null){
//            throw new RuntimeException("Student with ID" + courseStudentRequest.getBannerId() + "taking course with with courseID " + courseStudentRequest.getCourseId() + " already enrolled in it.");
//        }
        return courseStudentRepository.save(courseStudent);
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
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(courseStudentRequest.getBannerId(), courseStudentRequest.getCourseId());
        if (courseStudent == null) {
            throw new RuntimeException("Student with ID" + courseStudentRequest.getBannerId() + "taking course with with courseID " + courseStudentRequest.getCourseId() + " is not enrolled in it.");
        }
        courseStudentRepository.delete(courseStudent);
    }

    public List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) {

        // Create a list of courses to save to the database
        List<CourseStudent> students = new ArrayList<>();
        List<String> bannerIds = courseStudentRequests.getBannerIds();
        // Loop through the course requests and create a new Course object for each one
        for (String bannerId : bannerIds) {
            String courseId = courseStudentRequests.getCourseId();
            if (checkCourseStatus(teacherId, courseId, bannerId)) {
                CourseStudent courseStudent = new CourseStudent();
                students.add(courseStudent);
            }
        }
            // Save the courses to the database
            return courseStudentRepository.saveAll(students);

    }
    public void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests){

      //  List<CourseStudent> courseStudents= courseStudentRepository.findByStudentBannerIdAndCourseCourseId();
    }

    public boolean checkCourseStatus(String teacherId, String courseId, String bannerId){
            Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
            if (course == null) {
                throw new RuntimeException("Teacher with ID " + teacherId + " taking course with courseID " + courseId + " not found.");
            }
            CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
            if (courseStudent == null) {
                return false;
            }
            return true;
        }

    }

