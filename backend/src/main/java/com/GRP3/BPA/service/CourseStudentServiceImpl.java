package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.exceptions.GlobalException;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.courseStudent.CourseStudentRepository;
import com.GRP3.BPA.repository.student.StudentRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CourseStudentServiceImpl implements CourseStudentService {
    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private CourseStudentRepository courseStudentRepository;

    /**
     * @param courseStudentRequest
     * @return
     */
    @Override
    public CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws GlobalException {
        CourseStudent courseStudent = new CourseStudent(); //push this in if after creating response
        String courseId = courseStudentRequest.getCourseId();
        String bannerId = courseStudentRequest.getBannerId();
        if (!checkCourseStatus(teacherId, courseId, bannerId)) {
            Student student = studentRepository.findByBannerId(bannerId);
            Course course = courseRepository.findByCourseId(courseId);
            courseStudent.setStudent(student);
            courseStudent.setCourse(course);
            courseStudent.setPoints(0);
            courseStudentRepository.save(courseStudent);
        } else {
            // made change to solve Long Statement Smell but couldn't solve it
            String bannerID=courseStudentRequest.getBannerId();
            String courseID=courseStudentRequest.getCourseId();
            String message="Student with ID "+bannerID+" taking course with with courseID "+courseID+" already enrolled in it.";
            throw new GlobalException(false,message);
        }
        return courseStudent;
    }

    /**
     * @param courseStudentRequest
     */
    @Override
    public void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws GlobalException {
        String courseId = courseStudentRequest.getCourseId();
        String bannerId = courseStudentRequest.getBannerId();
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
        courseStudentRepository.delete(courseStudent);


    }

    public List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws GlobalException {

        // Create a list of courses to save to the database
        List<CourseStudent> studentList = new ArrayList<>();
        List<String> bannerIds = courseStudentRequests.getBannerIds();
        String courseId = courseStudentRequests.getCourseId();
        for (String bannerId : bannerIds) {
            CourseStudentRequest courseStudentRequest = new CourseStudentRequest(bannerId,courseId);
            CourseStudent courseStudent = addStudent(teacherId, courseStudentRequest);
            studentList.add(courseStudent);
        }
        return studentList;
    }

    public void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws GlobalException {
        List<String> bannerIds = courseStudentRequests.getBannerIds();
        String courseId = courseStudentRequests.getCourseId();
        for (String bannerId : bannerIds) {
            CourseStudentRequest courseStudentRequest = new CourseStudentRequest(courseId, bannerId);
            removeStudent(teacherId, courseStudentRequest);
        }
    }

    public boolean checkCourseStatus(String teacherId, String courseId, String bannerId) throws GlobalException {
        Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
        if (course == null) {
            String message="Teacher with ID " + teacherId + " taking course with courseID " + courseId + " not found.";
            throw new GlobalException(false,message);
        }

        CourseStudent ds = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
        if (ds != null) {
            String message="Student with ID " + bannerId + " taking course with courseID " + courseId + " already exists.";
            throw new GlobalException(false,message);
        }
        return false;
    }



}

