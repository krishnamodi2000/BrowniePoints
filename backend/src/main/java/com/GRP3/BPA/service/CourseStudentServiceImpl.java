package com.GRP3.BPA.service;

import com.GRP3.BPA.model.course.Course;
import com.GRP3.BPA.model.courseStudent.*;
import com.GRP3.BPA.model.student.Student;
import com.GRP3.BPA.model.courseStudent.StudentInfoWithName;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.courseStudent.CourseStudentRepository;
import com.GRP3.BPA.repository.student.StudentRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    public CourseStudentServiceImpl(TeacherRepository teacherRepository,CourseRepository courseRepository,CourseStudentRepository courseStudentRepository, StudentRepository studentRepository) {
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
        CourseStudent courseStudent = new CourseStudent(); //push this in if after creating response
        String courseId = courseStudentRequest.getCourseId();
        String bannerId = courseStudentRequest.getBannerId();
        if (!checkCourseStatus(teacherId, courseId, bannerId)) {
            Student student = studentRepository.findByBannerId(bannerId);
            Course course = courseRepository.findByCourseId(courseId);
            courseStudent.setStudent(student);
            courseStudent.setCourse(course);
            courseStudentRepository.save(courseStudent);
        } else {
            throw new RuntimeException("Student with ID" + courseStudentRequest.getBannerId() + "taking course with with courseID " + courseStudentRequest.getCourseId() + " already enrolled in it.");
        }
        return courseStudent;
    }

    /**
     * @param courseStudentRequest
     */
    @Override
    public void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) {
        String courseId = courseStudentRequest.getCourseId();
        String bannerId = courseStudentRequest.getBannerId();
        if (!checkCourseStatus(teacherId, courseId, bannerId)) {
            CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
            courseStudentRepository.delete(courseStudent);
        }

    }

    public List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) {
        // Create a list of courses to save to the database
        List<CourseStudent> studentList = new ArrayList<>();
        List<String> bannerIds = courseStudentRequests.getBannerIds();
        String courseId = courseStudentRequests.getCourseId();
        for (String bannerId : bannerIds) {
            CourseStudentRequest courseStudentRequest = new CourseStudentRequest(courseId, bannerId);
            CourseStudent courseStudent = addStudent(teacherId, courseStudentRequest);
            studentList.add(courseStudent);
        }
        return studentList;
    }

    public void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) {
        List<String> bannerIds = courseStudentRequests.getBannerIds();
        String courseId = courseStudentRequests.getCourseId();
        for (String bannerId : bannerIds) {
            CourseStudentRequest courseStudentRequest = new CourseStudentRequest(courseId, bannerId);
            removeStudent(teacherId, courseStudentRequest);
        }
    }

    public boolean checkCourseStatus(String teacherId, String courseId, String bannerId) {
        Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
        if (course == null) {
            throw new RuntimeException("Teacher with ID " + teacherId + " taking course with courseID " + courseId + " not found.");
        }
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
        if (courseStudent != null) {
            return true;
        }
        return false;
    }

    public PointsCreateResponse incrementPoints(String studentId, String courseId)
    {
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(studentId, courseId);

        if(courseStudent != null){
            int currentPoints = courseStudent.getPoints() +1;
            courseStudent.setPoints(currentPoints);
            courseStudentRepository.save(courseStudent);
        }

        PointsCreateResponse pointsCreateResponse = new PointsCreateResponse();
        pointsCreateResponse.setSuccess(true);

        pointsCreateResponse.setStudent(studentRepository.findByBannerId(studentId));

        return pointsCreateResponse;
    }

    public CourseStudentsResponse dataOfStudent(String courseId){
        List<CourseStudent> courseStudents = courseStudentRepository.findByCourseCourseId(courseId);
        CourseStudentsResponse courseStudentsResponse = new CourseStudentsResponse();
        ArrayList<StudentInfoWithName> data = new ArrayList<>();

        if(courseStudents != null){
            for(int i=0; i<courseStudents.size(); i++){
                CourseStudent courseStudent = courseStudents.get(i);
                Student student = courseStudent.getStudent();
                StudentInfoWithName studentInfoWithName = new StudentInfoWithName();
                studentInfoWithName.setStudentName(student.getUser().getFirstName() + " " +student.getUser().getLastName());
                studentInfoWithName.setBannerId(student.getBannerId());
                studentInfoWithName.setPoints(courseStudent.getPoints());
                data.add(studentInfoWithName);
            }
            courseStudentsResponse.setSuccess(true);
            courseStudentsResponse.setData(data);
        }
        return courseStudentsResponse;
    }
}

