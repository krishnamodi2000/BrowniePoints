package com.GRP3.BPA.service.ServiceInterface;

import com.GRP3.BPA.exceptions.CustomizableException;
import com.GRP3.BPA.model.CourseStudent;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequest;
import com.GRP3.BPA.request.courseStudent.CourseStudentRequests;

import java.util.ArrayList;
import java.util.List;

public interface CourseStudentService {

    /**
     * Get a list of all students enrolled in a course.
     *
     * @param teacherId The ID of the teacher who owns the course.
     * @param courseId  The ID of the course.
     * @return A list of CourseStudent objects representing the students in the course.
     * @throws CustomizableException If there was an error retrieving the students.
     */
    List<CourseStudent> getStudentsForACourse(String teacherId, String courseId) throws CustomizableException;

    /**
     * Add a new student to a course.
     *
     * @param teacherId           The ID of the teacher who owns the course.
     * @param courseStudentRequest A CourseStudentRequest object containing the student's information.
     * @return The CourseStudent object representing the new student.
     * @throws CustomizableException If there was an error adding the student.
     */
    CourseStudent addStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws CustomizableException;

    /**
     * Remove a student from a course.
     *
     * @param teacherId           The ID of the teacher who owns the course.
     * @param courseStudentRequest A CourseStudentRequest object containing the student's information.
     * @throws CustomizableException If there was an error removing the student.
     */
    void removeStudent(String teacherId, CourseStudentRequest courseStudentRequest) throws CustomizableException;

    /**
     * Add multiple students to a course.
     *
     * @param teacherId              The ID of the teacher who owns the course.
     * @param courseStudentRequests A CourseStudentRequests object containing a list of CourseStudentRequest objects.
     * @return A list of CourseStudent objects representing the new students.
     * @throws CustomizableException If there was an error adding the students.
     */
    List<CourseStudent> addStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws CustomizableException;

    /**
     * Remove multiple students from a course.
     *
     * @param teacherId              The ID of the teacher who owns the course.
     * @param courseStudentRequests A CourseStudentRequests object containing a list of CourseStudentRequest objects.
     * @throws CustomizableException If there was an error removing the students.
     */
    void removeStudents(String teacherId, CourseStudentRequests courseStudentRequests) throws CustomizableException;

}
