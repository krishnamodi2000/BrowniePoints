package com.GRP3.BPA.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {

        @Test
        void testGetBannerId() {
            Student student = new Student();
            student.setBannerId("123456");
            Assertions.assertEquals("123456", student.getBannerId());
        }

        @Test
        void testGetSetUser() {
            Student student = new Student();
            User user = new User();
            student.setUser(user);
            Assertions.assertEquals(user, student.getUser());
        }
}
