package com.GRP3.BPA.service;

import com.GRP3.BPA.response.points.StudentPointsAllSubject;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.response.points.PointsCreateResponse;
import com.GRP3.BPA.response.courseStudent.StudentInfoWithName;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.response.courseStudent.CourseStudentsResponse;
import com.GRP3.BPA.service.ServiceInterface.PointService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class PointServiceImpl implements PointService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    /**
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public PointsCreateResponse incrementPoints(String studentId, String courseId) {
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

    /**
     * @param courseId
     * @return
     */
    @Override
    public CourseStudentsResponse dataOfStudent(String courseId) {
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

    /**
     * @param bannerId
     * @return
     */
    @Override
    public List<StudentPointsAllSubject> pointsAllSubject(String bannerId) {
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
