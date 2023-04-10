package com.GRP3.BPA.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


/**
 * Data Transfer Object for User information
 */
public class UserDTO {

    /**
     * Constructor for UserDTO
     * @param id ID of the user
     * @param userId User ID of the user
     * @param email Email address of the user
     * @param firstName First name of the user
     * @param lastName Last name of the user
     * @param role Role of the user
     * @param token Authentication token of the user
     */
    public UserDTO(int id, String userId, String email, String firstName, String lastName,
                    String role, String token) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.token = token;
    }

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String userId;

    @Getter
    @Setter
    private String email;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;


    @Getter @Setter
    private String role;

    @Getter@Setter
    private String token;
}
