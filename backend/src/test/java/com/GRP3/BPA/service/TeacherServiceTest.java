package com.GRP3.BPA.service;

import com.GRP3.BPA.model.Teacher;
import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.TeacherRepository;
import com.GRP3.BPA.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    public void testFindTeacherAssociatedWithUserValidEmail() {
        // Arrange
        String email = "teacher@example.com";
        User user = new User();
        user.setUserId("1");
        user.setEmail(email);
        user.setRole("ROLE_TEACHER");
        when(userRepository.findByEmail(email)).thenReturn(user);

        Teacher teacher = new Teacher();
        teacher.setTeacherId("T001");
        teacher.setUser(user);
        when(teacherRepository.findByUserId(user.getId())).thenReturn(teacher);

        // Act
        String teacherId = teacherService.findTeacherAssociatedWithUser(email);

        // Assert
        assertEquals("T001", teacherId);
    }

    // Add more tests here...
}

