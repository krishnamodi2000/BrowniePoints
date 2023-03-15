package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.model.TeacherCourse;
import com.GRP3.BPA.repository.course.CourseRepository;
import com.GRP3.BPA.repository.courseStudent.CourseStudentRepository;
import com.GRP3.BPA.repository.student.StudentRepository;
import com.GRP3.BPA.repository.teacherCourse.TeacherCourseRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseStudentServiceImpl implements CourseStudentService {

    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    private TeacherCourseRepository teacherCourseRepository;

    private  CourseStudentRepository courseStudentRepository;

    public CourseStudentServiceImpl(CourseRepository courseRepository,
                                    StudentRepository studentRepository,
                                    TeacherCourseRepository teacherCourseRepository,
                                    CourseStudentRepository courseStudentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherCourseRepository = teacherCourseRepository;
        this.courseStudentRepository = courseStudentRepository;
    }

    /**
     * @param courseId
     * @param teacherId
     * @param studentId
     */
    @Override
    public CourseStudent addStudent(String courseId, String teacherId, String studentId) {
        Course course = courseRepository.findById(courseId);
        TeacherCourse teacherCourse = teacherCourseRepository.findByTeacherIdAndCourseIdIn(teacherId, courseId);
        Student student = studentRepository.findById(studentId);
    if(teacherCourse!=null){
    CourseStudent courseStudent = new CourseStudent();
    courseStudent.setStudent(student);
    courseStudent.setCourse(course);
    return courseStudentRepository.save(courseStudent);
        }
       return null;
    }

    /**
     * @param courseId
     * @param teacherId
     * @param studentId
     */
    @Override
    public void removeStudent(String courseId, String teacherId, String studentId) {
        Course course = courseRepository.findById(courseId);
        TeacherCourse teacherCourse = teacherCourseRepository.findByTeacherIdAndCourseIdIn(teacherId, courseId);
        Student student = studentRepository.findById(studentId);
        if(teacherCourse!=null){
            CourseStudent courseStudent = courseStudentRepository.findByStudentIdAndCourseIdIn(studentId,courseId);
            courseStudentRepository.delete(courseStudent);
        }

    }

    /**
     * @param courseId
     * @param teacherId
     * @param csv
     */
    @Override
    public void addStudentsFromCsv(String courseId, String teacherId, InputStream csv) {
        Course course = courseRepository.findById(courseId);
        TeacherCourse teacherCourse = teacherCourseRepository.findByTeacherIdAndCourseIdIn(teacherId, courseId);
if(teacherCourse!=null){
    List<String> studentIds = parseCsv(csv);

    for (String studentId : studentIds) {
        Student student = studentRepository.findById(studentId);
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudentRepository.save(courseStudent);
    }
}
        // parse the CSV and extract the student ids

    }

    private List<String> parseCsv(InputStream csv) {
        List<String> studentIds = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(csv))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Each line of the CSV file should contain only one value, which is the student id
                if (nextLine.length != 1) {
                    throw new IllegalArgumentException("Invalid CSV file format");
                }

                String studentId = String.valueOf(nextLine[0]);
                studentIds.add(studentId);
            }
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse CSV file", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return studentIds;
    }

}
