package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.repository.StudentRepository;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;
import com.GRP3.BPA.service.ServiceInterface.CourseStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an implementation for the CourseStudentService interface, which defines the business logic
 * for managing the relationship between a course and its students. It contains methods for adding/removing
 * students from a course, retrieving a list of students for a course, and checking if a teacher is authorized
 * to manage a particular course.
 */
@Service
@Transactional
@AllArgsConstructor
public class CourseStudentServiceImpl implements CourseStudentService {

    // Autowire repositories and dependencies
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private CourseStudentRepository courseStudentRepository;

    /**
     * Returns a list of students for a given course
     *
     * @param teacherId ID of the teacher
     * @param courseId  ID of the course
     * @return list of CourseStudent objects representing the students enrolled in the course
     * @throws CustomizableException if teacher is not associated with the course
     */
    @Override
    public List<CourseStudent> getStudentsForACourse(String teacherId, String courseId) throws CustomizableException {
        //check if the teacher takes this particular course or throw exception
        checkIfTeacherTakesCourse(teacherId, courseId);

        //return the list of students for the course
        return courseStudentRepository.findByCourseCourseId(courseId);
    }

    /**
     * Adds a single student to a course
     *
     * @param teacherId            ID of the teacher
     * @param courseStudentRequest request object containing banner ID and course ID
     * @return CourseStudent object representing the added student
     * @throws CustomizableException if teacher is not associated with the course, or if the student is already enrolled in the course
     */
    @Override
    public CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws CustomizableException {

        String courseId = courseStudentRequest.getCourseId();
        String bannerId = courseStudentRequest.getBannerId();

        // Check if the teacher takes the course
        checkIfTeacherTakesCourse(teacherId, courseId);

        //check if the student exists
        checkIfStudentExists(bannerId);

        // Check if the student is already enrolled in the course
        checkIfStudentIsEnrolledInCourse(bannerId, courseId);

        // Create a new CourseStudent object
        CourseStudent courseStudent = new CourseStudent();

        // Get the student by their banner ID
        Student student = studentRepository.findByBannerId(bannerId);
        courseStudent.setStudent(student);

        // Get the course by its ID
        Course course = courseRepository.findByCourseId(courseId);
        courseStudent.setCourse(course);

        // Set the initial points to 0
        courseStudent.setPoints(0);

        // Save the CourseStudent object to the database and return it
        return courseStudentRepository.save(courseStudent);
    }

    /**
     * Removes a single student from a course
     *
     * @param teacherId            ID of the teacher
     * @param courseStudentRequest request object containing student ID and course ID
     * @throws CustomizableException if teacher is not associated with the course, or if the student is not enrolled in the course
     */
    @Override
    public void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws CustomizableException {
        String courseId = courseStudentRequest.getCourseId();
        String bannerId = courseStudentRequest.getBannerId();

        // Check if the teacher takes the course
        checkIfTeacherTakesCourse(teacherId, courseId);
        checkIfStudentExists(bannerId);
        //delete the student from the course in the CourseStudent from the database
        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
        if (courseStudent == null) {
            //throw exception if there is no such student enrolled  in the course
            boolean status = false;
            String message = "No student with bannerId: " + bannerId + " is enrolled in course: " + courseId;
            throw new CustomizableException(status, message);

        }
        courseStudentRepository.delete(courseStudent);

    }

    /**
     * This method adds students to a course by creating a list of CourseStudent objects and saving them to the database.
     *
     * @param teacherId             The ID of the teacher adding the students.
     * @param courseStudentRequests A CourseStudentRequests object containing the IDs of the course and students to add.
     * @return A list of CourseStudent objects representing the added students.
     * @throws CustomizableException If the teacher is not assigned to the given course, or if a student is already enrolled in the course.
     */
    public List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws CustomizableException {

        String courseId = courseStudentRequests.getCourseId();

        //check if the teacher takes the course
        checkIfTeacherTakesCourse(teacherId, courseId);

        // Create a list of courses to save to the database
        List<CourseStudent> studentList = new ArrayList<>();

        //Create a list of banner Ids to parse through
        List<String> bannerIds = courseStudentRequests.getBannerIds();

        for (String bannerId : bannerIds) {

            //check if student is already enrolled in the course
            checkIfStudentIsEnrolledInCourse(bannerId, courseId);

            checkIfStudentExists(bannerId);

            //create a new Course Student object
            CourseStudent courseStudent = new CourseStudent();

            //Get the student by bannerId...//add exception here
            Student student = studentRepository.findByBannerId(bannerId);
            courseStudent.setStudent(student);

            //Get the course by courseId
            Course course = courseRepository.findByCourseId(courseId);
            courseStudent.setCourse(course);

            //set initial points to zero
            courseStudent.setPoints(0);

            //add it to the list to add students in the CourseStudents
            studentList.add(courseStudent);
        }

        // save all students to the database and return it
        return courseStudentRepository.saveAll(studentList);
    }

    /**
     * This method removes students from a course by deleting the corresponding CourseStudent objects from the database.
     *
     * @param teacherId             The ID of the teacher removing the students.
     * @param courseStudentRequests A CourseStudentRequests object containing the IDs of the course and students to remove.
     * @throws CustomizableException If the teacher is not assigned to the given course, or if a student is not enrolled in the course.
     */

    public void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws CustomizableException {
        String courseId = courseStudentRequests.getCourseId();

        //check if the teacher takes the course
        checkIfTeacherTakesCourse(teacherId, courseId);

        //Create a list of banner Ids to parse through
        List<String> bannerIds = courseStudentRequests.getBannerIds();

        // Create a list of courses to delete from  the database
        List<CourseStudent> studentList = new ArrayList<>();

        for (String bannerId : bannerIds) {
            //add to the list to delete the student from the course in the
            // CourseStudent from the database
            checkIfStudentExists(bannerId);
            CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
            if (courseStudent == null) {
                boolean status = false;
                String message = "No student with bannerId: " + bannerId + " is enrolled in course: " + courseId;
                throw new CustomizableException(status, message);

            }

            studentList.add(courseStudent);
            //throw an exception if the student doesn't take the course

        }

        courseStudentRepository.deleteAll(studentList);
    }

    /**
     * This method checks if a teacher is assigned to a given course.
     *
     * @param teacherId The ID of the teacher to check.
     * @param courseId  The ID of the course to check.
     * @throws CustomizableException If the teacher is not assigned to the course.
     */
    private void checkIfTeacherTakesCourse(String teacherId, String courseId) throws CustomizableException {
        Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
        if (course == null) {
            String message = "Teacher with ID " + teacherId + " taking course with courseID " + courseId + " not found.";
            throw new CustomizableException(false, message);
        }
    }

    /**
     * Checks if the student is already enrolled in the given course.
     * Throws CustomizableException if the student is already enrolled in the course.
     *
     * @param bannerId The unique identifier of the student.
     * @param courseId The unique identifier of the course.
     * @throws CustomizableException If the student is already enrolled in the course.
     */

    private void checkIfStudentIsEnrolledInCourse(String bannerId, String courseId) throws CustomizableException {

        CourseStudent courseStudent = courseStudentRepository.findByStudentBannerIdAndCourseCourseId(bannerId, courseId);
        if (courseStudent != null) {
            String message = "Student with ID " + bannerId + " taking course with courseID " + courseId + " already exists.";
            throw new CustomizableException(false, message);
        }
    }
    private void checkIfStudentExists(String bannerId) throws CustomizableException {
        Student student= studentRepository.findByBannerId(bannerId);
        if(student == null){
            boolean status = false;
            String message = "The student " + bannerId + " does not exists and ";
            throw  new CustomizableException(status,message);
        }
    }

}

