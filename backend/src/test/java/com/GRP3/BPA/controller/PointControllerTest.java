package com.GRP3.BPA.controller;

import com.GRP3.BPA.model.*;
import com.GRP3.BPA.service.PointServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import java.util.ArrayList;


public class PointControllerTest {
    @InjectMocks
    private PointServiceImpl pointService;
    User userIsStudent;
    User isTeacher;
    Student student;
    Course course;
    @BeforeEach
    public void setUp() {

        userIsStudent = new User();
        student=new Student();
        student.setBannerId("1");
        student.setUser(userIsStudent);


        course = new Course();
        course.setCourseId("1");
        course.setCourseName("ASDC");
        course.setCourseDescription("A course on ASDC");
        course.setTeacher(teacher);


    }
}