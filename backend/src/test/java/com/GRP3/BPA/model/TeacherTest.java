package com.GRP3.BPA.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TeacherTest {

    @Test
    void testGetTeacherId() {
        Teacher teacher = new Teacher();
        teacher.setTeacherId("T001");
        Assertions.assertEquals("T001", teacher.getTeacherId());
    }

    @Test
    void testGetSetUser() {
        Teacher teacher = new Teacher();
        User user = new User();
        teacher.setUser(user);
        Assertions.assertEquals(user, teacher.getUser());
    }
}
