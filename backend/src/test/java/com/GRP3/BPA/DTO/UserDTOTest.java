package com.GRP3.BPA.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class UserDTOTest {

    @Test
    void testConstructorAndGetters() {

        int id = 1;
        String userId = "user123";
        String email = "test@example.com";
        String firstName = "John";
        String lastName = "Doe";
        String role = "user";
        String token = "abc123";

        UserDTO user = new UserDTO(id, userId, email, firstName, lastName, role, token);

        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(userId, user.getUserId());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());
        Assertions.assertEquals(role, user.getRole());
        Assertions.assertEquals(token, user.getToken());
    }

    @Test
    void testSetters() {

        UserDTO user = new UserDTO(1, "user123", "test@example.com", "John", "Doe", "user", "abc123");

        user.setUserId("user456");
        user.setEmail("newtest@example.com");
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setRole("admin");
        user.setToken("xyz789");

        Assertions.assertEquals("user456", user.getUserId());
        Assertions.assertEquals("newtest@example.com", user.getEmail());
        Assertions.assertEquals("Jane", user.getFirstName());
        Assertions.assertEquals("Smith", user.getLastName());
        Assertions.assertEquals("admin", user.getRole());
        Assertions.assertEquals("xyz789", user.getToken());
    }

    @Test
    void testIdGenerated() {

        UserDTO user1 = new UserDTO(0, "user123", "test1@example.com", "John", "Doe", "user", "abc123");
        UserDTO user2 = new UserDTO(0, "user456", "test2@example.com", "Jane", "Smith", "admin", "xyz789");
        Assertions.assertFalse(user1.getId() > 0);
        Assertions.assertFalse(user2.getId() > 0);
    }

    @Test
    void testFieldValidation() {

    }

    @Test
    void testEquality() {
        // Arrange
        UserDTO user1 = new UserDTO(1, "user123", "test@example.com", "John", "Doe", "user", "abc123");
        UserDTO user2 = new UserDTO(1, "user123", "test@example.com", "John", "Doe", "user", "abc123");

        // Act & Assert
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getFirstName(), user2.getFirstName());
        assertEquals(user1.getLastName(), user2.getLastName());
        assertEquals(user1.getRole(), user2.getRole());
    }

}
