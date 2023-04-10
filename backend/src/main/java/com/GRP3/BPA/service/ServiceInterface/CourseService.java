package com.GRP3.BPA.service.ServiceInterface;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.Course;
import com.GRP3.BPA.request.course.CourseRequest;

import java.util.List;

public interface CourseService {

    /**
     * Gets all the courses for a given teacher
     *
     * @param teacherId the ID of the teacher whose courses to get
     * @return a list of Course objects representing the teacher's courses
     * @throws CustomizableException if there is an error retrieving the courses
     */
    List<Course> getCoursesForTeacher(String teacherId) throws CustomizableException;

    /**
     * Adds a course for a given teacher
     *
     * @param teacherId the ID of the teacher to add the course for
     * @param courseRequest a CourseRequest object containing the details of the course to add
     * @return a Course object representing the added course
     * @throws CustomizableException if there is an error adding the course
     */
    Course addCourseForTeacher(String teacherId, CourseRequest courseRequest) throws CustomizableException;

    /**
     * Adds multiple courses for a given teacher
     *
     * @param teacherId the ID of the teacher to add the courses for
     * @param courseRequests a list of CourseRequest objects containing the details of the courses to add
     * @return a list of Course objects representing the added courses
     * @throws CustomizableException if there is an error adding the courses
     */
    List<Course> addCoursesForTeacher(String teacherId, List<CourseRequest> courseRequests) throws CustomizableException;

    /**
     * Removes a course for a given teacher
     *
     * @param teacherId the ID of the teacher to remove the course for
     * @param courseId the ID of the course to remove
     * @throws CustomizableException if there is an error removing the course
     */
    void removeCourseForTeacher(String teacherId, String courseId) throws CustomizableException;

    /**
     * Removes multiple courses for a given teacher
     *
     * @param teacherId the ID of the teacher to remove the courses for
     * @param courseIds a list of course IDs representing the courses to remove
     * @throws CustomizableException if there is an error removing the courses
     */
    void removeCoursesForTeacher(String teacherId, List<String> courseIds) throws CustomizableException;

    /**
     * Updates a course for a given teacher
     *
     * @param teacherId the ID of the teacher to update the course for
     * @param courseRequest a CourseRequest object containing the updated details of the course
     * @return a Course object representing the updated course
     * @throws CustomizableException if there is an error updating the course
     */
    Course updateCourseForTeacher(String teacherId, CourseRequest courseRequest) throws CustomizableException;

    }
