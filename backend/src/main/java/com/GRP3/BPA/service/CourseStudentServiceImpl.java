package com.GRP3.BPA.service;

import com.GRP3.BPA.model.*;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.courseStudent.CourseStudentRepository;
import com.GRP3.BPA.repository.student.StudentRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private  final CourseStudentRepository courseStudentRepository;



    public CourseStudentServiceImpl(TeacherRepository teacherRepository,CourseRepository courseRepository,CourseStudentRepository courseStudentRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseStudentRepository=courseStudentRepository;
    }
    /**
     * @param courseStudentRequest
     * @return
     */
    @Override
    public CourseStudent addStudent(CourseStudentRequest courseStudentRequest) {
        Course course= courseRepository.findByTeacherTeacherIdAndCourseId(courseStudentRequest.getTeacherId(),courseStudentRequest.getCourseId());
        if(course==null){
            throw new RuntimeException("Teacher with ID"+courseStudentRequest.getTeacherId()+"taking course with with courseID " + courseStudentRequest.getCourseId() + " not found.");
        }
        CourseStudent courseStudent= courseStudentRepository.findByStudentBannerIdAndCourseCourseId(courseStudentRequest.getStudentId(),courseStudentRequest.getCourseId());
        if(courseStudent!=null){
            throw new RuntimeException("Student with ID"+courseStudentRequest.getStudentId()+"taking course with with courseID " + courseStudentRequest.getCourseId() + " already enrolled in it.");
        }
        Student student= studentRepository.findByBannerId(courseStudentRequest.getStudentId());
        courseStudent=new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setStudent(student);

        return courseStudentRepository.save(courseStudent);
    }

    /**
     * @param courseStudentRequest
     */
    @Override
    public void removeStudent(CourseStudentRequest courseStudentRequest) {
        Course course= courseRepository.findByTeacherTeacherIdAndCourseId(courseStudentRequest.getTeacherId(), courseStudentRequest.getCourseId());
        if(course==null){
            throw new RuntimeException("Teacher with ID"+courseStudentRequest.getTeacherId()+"taking course with with courseID " + courseStudentRequest.getCourseId() + " not found.");
        }
        CourseStudent courseStudent= courseStudentRepository.findByStudentBannerIdAndCourseCourseId(courseStudentRequest.getStudentId(),courseStudentRequest.getCourseId());
        if(courseStudent==null){
            throw new RuntimeException("Student with ID"+courseStudentRequest.getStudentId()+"taking course with with courseID " + courseStudentRequest.getCourseId() + " is not enrolled in it.");
        }
        courseStudentRepository.delete(courseStudent);
    }


    public void addStudentsFromCsv(File input) {
        List<CourseStudent> courseStudents = new ArrayList<>(); // create a list to store students

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) { // read each line of the CSV file
                String[] fields = line.split(","); // split the line into fields
                String bannerId = fields[0];
                Student student= studentRepository.findByBannerId(bannerId);
                String courseId = fields[1];
                Course course= courseRepository.findByCourseId(courseId);
                CourseStudent courseStudent= courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId,courseId);
                if(courseStudent==null){
                    courseStudent=new CourseStudent();
                    courseStudent.setStudent(student);
                    courseStudent.setCourse(course);
                    courseStudents.add(courseStudent);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        courseStudentRepository.saveAll(courseStudents);
        // do something with the list of students, e.g. add them to a database or display them on the screen
    }

    @Override
    public void removeStudentsFromCsv(File input) {
        List<CourseStudent> courseStudents = new ArrayList<>(); // create a list to store students

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) { // read each line of the CSV file
                String[] fields = line.split(","); // split the line into fields
                String bannerId = fields[0];
                String courseId = fields[1];
                CourseStudent courseStudent= courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId,courseId);
                if(courseStudent!=null){
                    courseStudents.add(courseStudent);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        courseStudentRepository.deleteAll(courseStudents);
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
