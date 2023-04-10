package com.GRP3.BPA.service;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.repository.CourseStudentRepository;
import com.GRP3.BPA.request.course.CourseRequest;
import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.repository.CourseRepository;
import com.GRP3.BPA.repository.TeacherRepository;
import com.GRP3.BPA.service.ServiceInterface.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an implementation for the CourseService interface, which defines the business logic
 * for managing the relationship between a teacher and its courses. It contains methods for adding/removing
 * a course, retrieving a list of courses for a teacher, updating the course details and checking if a teacher is authorized
 * to manage a particular course.
 */
@Service
@Transactional
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    // Autowire the necessary repositories
    @Autowired
    private final TeacherRepository teacherRepository;
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final CourseStudentRepository courseStudentRepository;


    /**
     * Method to get all courses for a given teacher
     *
     * @param teacherId id of the teacher
     * @return list of courses for the teacher
     * @throws CustomizableException if the teacher does not exist or does not take any courses
     */
    @Override
    public List<Course> getCoursesForTeacher(String teacherId) throws CustomizableException {

        checkIfTeacherExists(teacherId);

        //get the list of courses taken by teacher
        List<Course> course = courseRepository.findByTeacherTeacherId(teacherId);

        //throw exception if the teacher doesn't take courses
        if (course.isEmpty()) {
            String message = "Teacher with teacherId:" + teacherId + "does not take any courses";
            throw new CustomizableException(false, message);
        }

        return course;
    }

    /**
     * Method to add a single course for a given teacher
     *
     * @param teacherId      id of the teacher
     * @param courseRequest  course details
     * @return the newly added course
     * @throws CustomizableException if the teacher or course already exist in the database
     */
    @Override
    public Course addCourseForTeacher(String teacherId, CourseRequest courseRequest) throws CustomizableException {
        // extract all fields from the courseRequest
        String courseId= courseRequest.getCourseId();
        String courseName= courseRequest.getCourseName();
        String courseDescription = courseRequest.getCourseDescription();

        // Get the teacher from the database and check if the teacher exists
        checkIfTeacherExists(teacherId);

        //check if the course exist in the database already
        checkIfCourseExists(courseId);

        // If course does not already exist create a new course object
        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseDescription(courseDescription);

        // Get the teacher from the database and set it to the course object
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
        course.setTeacher(teacher);

        // Save the course to the database
        return courseRepository.save(course);
    }

    /**
     * Method to add multiple courses for a given teacher
     *
     * @param teacherId       id of the teacher
     * @param courseRequests  list of course details
     * @return list of newly added courses
     * @throws CustomizableException if the teacher or course does not exist in the database
     */

    @Override
    public List<Course> addCoursesForTeacher(String teacherId, List<CourseRequest> courseRequests) throws CustomizableException {
        // Get the teacher from the database
        checkIfTeacherExists(teacherId);
        Teacher teacher= teacherRepository.findByTeacherId(teacherId);

        // Create a list of courses to save to the database
        List<Course> courses = new ArrayList<>();

        // Loop through the course requests and create a new Course object for each one
        for (CourseRequest courseRequest : courseRequests) {
            // extract all fields from the courseRequest
            String courseId= courseRequest.getCourseId();
            String courseName= courseRequest.getCourseName();
            String courseDescription = courseRequest.getCourseDescription();

            //check if the course does not exist in the database already
            checkIfCourseExists(courseId);

            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseName(courseName);
            course.setCourseDescription(courseDescription);
            course.setTeacher(teacher);
            courses.add(course);
        }
        // Save the courses to the database
        return courseRepository.saveAll(courses);
    }

    /**
     * Method to remove a course for a given teacher
     *
     * @param teacherId id of the teacher
     * @param courseId  id of the course
     * @throws CustomizableException if the course does not exist in the database or the teacher is not associated with the course
     *                          or if the students do not take the course
     */
    @Override
    public void removeCourseForTeacher(String teacherId, String courseId) throws CustomizableException {
        Course courseDelete = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);

        //if the teacher does not take the course then throw exception
        checkIfTeacherTakesCourse(teacherId,courseId);

        //if the teacher takes course, but it has enrolled students then throw exception
        checkIfStudentsTakeCourse(courseId);

        //delete the course if the teacher takes it, and it has no enrolled student
        courseRepository.delete(courseDelete);
    }

    /**
     * Service method to remove courses for a given teacher from the database
     * @param teacherId the id of the teacher
     * @param courseIds the ids of the courses to remove
     * @throws CustomizableException if the teacher does not take a particular course or if the course has enrolled students
     */
    @Override
    public void removeCoursesForTeacher(String teacherId, List<String> courseIds) throws CustomizableException {
        List<Course> coursesDelete = new ArrayList<>();
        //check if the teacher takes the particular course and if the course has students enrolled
        for (String courseId : courseIds) {

            //if the teacher does not take the course then throw exception
            checkIfTeacherTakesCourse(teacherId,courseId);

            //if the teacher takes course, but it has enrolled students then throw exception
            checkIfStudentsTakeCourse(courseId);

            //add the course to delete in the list
            Course course= courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);
            coursesDelete.add(course);
        }

        //delete the courses if the teacher takes it, and it has no enrolled students
        courseRepository.deleteAll(coursesDelete);
    }

    /**
     * Method to update a course for a given teacher
     * @param teacherId id of the teacher
     * @param courseRequest course details to update
     * @return the updated course
     * @throws CustomizableException if the teacher does not take the course or the course does not exist
     */
    @Override
    public Course updateCourseForTeacher(String teacherId, CourseRequest courseRequest) throws CustomizableException {
        // Extract course details from the request
        String courseId = courseRequest.getCourseId();

        // Check if the teacher takes the course
        checkIfTeacherTakesCourse(teacherId,courseId);

        // Find the course to update
        Course course = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);

        // Find any students who are enrolled in the course
        List<CourseStudent> courseStudents = courseStudentRepository.findByCourseCourseId(courseId);

        // Update the course details
        course.setCourseId(courseId);
        course.setCourseName(courseRequest.getCourseName());
        course.setCourseDescription(courseRequest.getCourseDescription());
        courseRepository.save(course);

        // If any students are enrolled, update their course reference
        if (courseStudents.size() > 0) {
            for (CourseStudent courseStudent : courseStudents) {
                courseStudent.setCourse(course);
            }
        }

        // Save all the course students to update their reference
        courseStudentRepository.saveAll(courseStudents);
        return course;
    }

    /**
     *
     * Checks if the given teacher ID exists in the system or not.
     * If the teacher ID does not exist, throws a CustomizableException with the appropriate error message.
     * @param teacherId the ID of the teacher to check
     * @throws CustomizableException if the teacher ID does not exist in the system
     *
     */
    private void checkIfTeacherExists(String teacherId) throws CustomizableException {
        // Find the teacher with the given ID
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);

        // If the teacher is null, that means there is no teacher with the given ID
        if (teacher == null) {
            boolean status = false;
            String message = "Teacher with teacherId:" + teacherId + " does not exist";

            // Throw a CustomizableException with the error message
            throw new CustomizableException(status, message);
        }
    }

    /**
     * Checks if a course with a given courseId already exists.
     * @param courseId The id of the course to check.
     * @throws CustomizableException if a course with the given courseId already exists.
    */
    private void checkIfCourseExists(String courseId) throws CustomizableException {
        Course course = courseRepository.findByCourseId(courseId);
        if(course != null){
            String teacherIdTakingCourse= course.getTeacher().getTeacherId();
            boolean status = false;
            String message = "The course " + courseId + " already exists and is being taken by " + teacherIdTakingCourse;
            throw  new CustomizableException(status,message);
        }
    }

    /**
     * Checks if there are any students enrolled in a course with a given courseId.
     * @param courseId The id of the course to check.
     * @throws CustomizableException if there are any students enrolled in the given course.
     */
    private void checkIfStudentsTakeCourse(String courseId) throws CustomizableException {
        List<CourseStudent> courseStudent = courseStudentRepository.findByCourseCourseId(courseId);

        if (courseStudent.size() > 0) {
            String message = "Some students are taking the course with:" + courseId;
            boolean status = false;
            throw new CustomizableException(status, message);
        }
    }

    /**
     * Checks if a teacher with a given teacherId takes a course with a given courseId.
     * @param teacherId The id of the teacher to check.
     * @param courseId The id of the course to check.
     * @throws CustomizableException if the teacher does not take the given course.
     */
    private void checkIfTeacherTakesCourse(String teacherId, String courseId) throws CustomizableException {
        Course courseDelete = courseRepository.findByTeacherTeacherIdAndCourseId(teacherId, courseId);

        if (courseDelete == null) {
            String message = "Teacher with teacherId:" + teacherId + "does not take course "+courseId;
            boolean status = false;
            throw new CustomizableException(status, message);
        }
    }

}
