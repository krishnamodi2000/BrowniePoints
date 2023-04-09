package com.GRP3.BPA.service;

import com.GRP3.BPA.model.StudentPointsAllSubject;
import com.GRP3.BPA.model.course.Course;
import com.GRP3.BPA.model.GlobalException;
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
    private CourseStudentRepository courseStudentRepository;



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
            CourseStudentRequest courseStudentRequest = new CourseStudentRequest(bannerId,courseId);
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
            courseStudentsResponse.setStatus(true);
            courseStudentsResponse.setData(data);
        }
        return courseStudentsResponse;
    }

    public List<StudentPointsAllSubject> pointsAllSubject(String bannerId){
        List<CourseStudent> allCoursesEnrolled = courseStudentRepository.findByStudentBannerId(bannerId);
        List<StudentPointsAllSubject> response = new ArrayList<>();

        if(allCoursesEnrolled != null){
            for(int i=0; i<allCoursesEnrolled.size(); i++){
                CourseStudent courseStudent = allCoursesEnrolled.get(i);
                Course course = courseStudent.getCourse();
                StudentPointsAllSubject studentPointsAllSubject = new StudentPointsAllSubject();
                studentPointsAllSubject.setCourseId(course.getCourseId());
                studentPointsAllSubject.setCourseName(course.getCourseName());
                studentPointsAllSubject.setPoints(courseStudent.getPoints());
                response.add(studentPointsAllSubject);
            }
        }
        return response;
    }
}

