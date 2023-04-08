package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.exceptions.GlobalException;
import com.GRP3.BPA.request.course.CourseRequest;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    private final CourseRepository courseRepository;


    public List<Course> getCoursesForTeacher(String teacherId) throws GlobalException {
        List<Course> course = courseRepository.findByTeacherTeacherId(teacherId);
        if(course.isEmpty()){
            String message="Teacher with teacherId:"+teacherId+"does not take any courses";
            throw new GlobalException(false, message);
        }
        return course;
    }
    public Course addCourseForTeacher(String teacherId, CourseRequest courseRequest) throws GlobalException {
        // Get the teacher from the database
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
        if(teacher==null){
            String message="Teacher with teacherId:"+teacherId+"does not exists";
            throw new GlobalException(false, message);
        }
        // Create a new course object
        Course course = new Course();
        course.setCourseId(courseRequest.getCourseId());
        course.setCourseName(courseRequest.getCourseName());
        course.setCourseDescription(courseRequest.getCourseDescription());

        // Set the teacher for the course
        course.setTeacher(teacher);

        // Save the course to the database
        return courseRepository.save(course);
    }

    public List<Course> addCoursesForTeacher(String teacherId, List<CourseRequest> courseRequests) throws GlobalException {
        // Get the teacher from the database
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
        if(teacher==null){
            String message="Teacher with teacherId:"+teacherId+"does not exists";
            throw new GlobalException(false, message);
        }
        // Create a list of courses to save to the database
        List<Course> courses = new ArrayList<>();

        // Loop through the course requests and create a new Course object for each one
        for (CourseRequest courseRequest : courseRequests) {
            Course course = new Course();
            course.setCourseId(courseRequest.getCourseId());
            course.setCourseName(courseRequest.getCourseName());
            course.setCourseDescription(courseRequest.getCourseDescription());
            course.setTeacher(teacher);
            courses.add(course);
        }
        // Save the courses to the database
        return courseRepository.saveAll(courses);
    }

    public void removeCourseForTeacher(String teacherId, String courseId) throws GlobalException {
        Course courseDelete = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
        if(courseDelete==null){
            String message="Teacher with teacherId:"+teacherId+"does not exists";
            throw new GlobalException(false, message);
        }
        courseRepository.delete(courseDelete);
    }

    public void removeCoursesForTeacher(String teacherId, List<String> courseIds) throws GlobalException {
        List<Course> coursesDelete = courseRepository.findByTeacherTeacherIdAndCourseIdIn(teacherId, courseIds);
        if(coursesDelete.isEmpty()){
            String message="Teacher with teacherId:"+teacherId+"does not exists";
            throw new GlobalException(false, message);
        }
        courseRepository.deleteAll(coursesDelete);
    }
}
