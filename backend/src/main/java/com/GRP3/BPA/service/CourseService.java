package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseRequest;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.model.TeacherRequest;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public CourseService(TeacherRepository teacherRepository,CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    public List<Course> getCoursesForTeacher(String teacherId) {
        return courseRepository.findByTeacherTeacherId(teacherId);
    }


    public Course addCourseForTeacher(CourseRequest courseRequest) {
        // Get the teacher from the database
        Teacher teacher = teacherRepository.findByTeacherId(courseRequest.getTeacherId());
        if (teacher == null) {
            throw new RuntimeException("Teacher with ID " + courseRequest.getTeacherId() + " not found.");
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

    public List<Course> addCoursesForTeacher(List<CourseRequest> courseRequests) {
// Get the teacher ID from the first course request
        String teacherId = courseRequests.get(0).getTeacherId();

        // Get the teacher from the database
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher with ID " + teacherId + " not found.");
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



     public void removeCourseForTeacher(CourseRequest courseRequest) {
            Course courseDelete= courseRepository.findByTeacherTeacherIdAndCourseId(courseRequest.getTeacherId(),courseRequest.getCourseId());
            courseRepository.delete(courseDelete);
    }

    public void removeCoursesForTeacher(List<CourseRequest> courseRequests) {
        String teacherId = courseRequests.get(0).getTeacherId();
        Teacher teacher= teacherRepository.findByTeacherId(teacherId);
        if (teacher == null) {
            throw new RuntimeException("Teacher with ID " + teacherId + " not found.");
        }

        // Create a list of courses to save to the database
        List<String> courseIds = new ArrayList<>();

        for (CourseRequest courseRequest : courseRequests) {
            courseIds.add(courseRequest.getCourseId());
        }

        List<Course> coursesDelete = courseRepository.findByTeacherTeacherIdAndCourseIdIn(teacherId, courseIds);
        courseRepository.deleteAll(coursesDelete);
    }
}
