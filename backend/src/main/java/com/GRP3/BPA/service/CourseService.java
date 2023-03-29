package com.GRP3.BPA.service;

import com.GRP3.BPA.model.course.Course;
import com.GRP3.BPA.model.course.CourseException;
import com.GRP3.BPA.model.course.CourseRequest;
import com.GRP3.BPA.model.course.CourseResponse;
import com.GRP3.BPA.model.teacher.Teacher;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.IssuerUriCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UserRepository userRepository;

    public CourseService(TeacherRepository teacherRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> getCoursesForTeacher(String teacherId) throws CourseException {
        List<Course> course = courseRepository.findByTeacherTeacherId(teacherId);
        if(course==null){
            String message="Teacher with teacherId:"+teacherId+"does not take any courses";
            throw new CourseException(false, message);
        }
        return course;
    }
    public Course addCourseForTeacher(String teacherId, CourseRequest courseRequest) {
        // Get the teacher from the database
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
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

    public List<Course> addCoursesForTeacher(String teacherId, List<CourseRequest> courseRequests) {
        // Get the teacher from the database
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
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

    public void removeCourseForTeacher(String teacherId, String courseId) {
        Course courseDelete = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
        courseRepository.delete(courseDelete);
    }

    public void removeCoursesForTeacher(String teacherId, List<String> courseIds) {
        List<Course> coursesDelete = courseRepository.findByTeacherTeacherIdAndCourseIdIn(teacherId, courseIds);
        courseRepository.deleteAll(coursesDelete);
    }
}
