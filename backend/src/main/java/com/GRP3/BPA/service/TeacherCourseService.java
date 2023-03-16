//package com.GRP3.BPA.service;
//
//import com.GRP3.BPA.model.Course;
//import com.GRP3.BPA.model.Teacher;
//import com.GRP3.BPA.model.TeacherCourse;
//import com.GRP3.BPA.repository.course.CourseRepository;
//import com.GRP3.BPA.repository.teacherCourse.TeacherCourseRepository;
//import com.GRP3.BPA.repository.teacher.TeacherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional
//public class TeacherCourseService {
////    @Autowired
////    private final TeacherCourseRepository teacherCourseRepository;
//    @Autowired
//    private final TeacherRepository teacherRepository;
//
//    @Autowired
//    private final CourseRepository courseRepository;
//
//    public TeacherCourseService(TeacherCourseRepository teacherCourseRepository, TeacherRepository teacherRepository,
//                                CourseRepository courseRepository) {
//        this.teacherCourseRepository = teacherCourseRepository;
//        this.teacherRepository = teacherRepository;
//        this.courseRepository = courseRepository;
//    }
//
//    public List<TeacherCourse> getCoursesForTeacher(String teacherId) {
//        return teacherCourseRepository.findByTeacherId(teacherId);
//    }
//
//    public TeacherCourse addCourseForTeacher(String teacherId, String courseId) {
//
//        Teacher teacher = teacherRepository.findById(teacherId);
//
//        Course course = courseRepository.findById(courseId);
//
//        TeacherCourse teacherCourse = new TeacherCourse();
//        teacherCourse.setTeacher(teacher);
//        teacherCourse.setCourse(course);
//
//        teacherCourseRepository.save(teacherCourse);
//        return teacherCourse;
//    }
//
//    public List<TeacherCourse> addCoursesForTeacher(String teacherId, List<String> courseIds) {
//
//        Teacher teacher = teacherRepository.findById(teacherId);
//
//        List<Course> courses = courseRepository.findAllById(courseIds);
//        if (courses.size() != courseIds.size()) {
//            throw new IllegalArgumentException("One or more courses not found");
//        }
//
//        List<TeacherCourse> teacherCourses = new ArrayList<>();
//        for (Course course : courses) {
//            TeacherCourse teacherCourse = new TeacherCourse();
//            teacherCourse.setTeacher(teacher);
//            teacherCourse.setCourse(course);
//            teacherCourses.add(teacherCourse);
//        }
//
//        teacherCourseRepository.saveAll(teacherCourses);
//        return teacherCourses;
//    }
//
//    public void removeCourseForTeacher(String teacherId, String courseId) {
//
//        TeacherCourse teacherCourse= teacherCourseRepository.findByTeacherIdAndCourseIdIn(teacherId, courseId);
//        teacherCourseRepository.delete(teacherCourse);
//    }
//
//    public void removeCoursesForTeacher(String teacherId, List<String> courseIds) {
//        List<TeacherCourse> teacherCourses = teacherCourseRepository.findByTeacherIdAndCourseIdIn(teacherId, courseIds);
//        teacherCourseRepository.deleteAll(teacherCourses);
//    }
//}
