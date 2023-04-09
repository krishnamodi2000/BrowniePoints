package com.GRP3.BPA.response.points;

import com.GRP3.BPA.model.Student;
import com.GRP3.BPA.model.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointsCreateResponseTest {
    @Test
    public void testGetSetSuccess() {
        PointsCreateResponse response = new PointsCreateResponse();
        response.setSuccess(true);
        assertTrue(response.isSuccess());
        response.setSuccess(false);
        assertFalse(response.isSuccess());
    }

    @Test
    public void testGetSetStudent() {
        PointsCreateResponse response = new PointsCreateResponse();
        Student student = new Student();
        student.setBannerId("s12345");
        User user = new User();
        user.setUserId("s12345");
        user.setEmail("test@dal.ca");
        student.setUser(user);
        response.setStudent(student);
        assertEquals(student, response.getStudent());
    }
}