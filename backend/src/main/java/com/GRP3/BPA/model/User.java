package com.GRP3.BPA.model;


import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

@Entity
@Table(name="User")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String contact_number;

    @Column
    private String university_id;

    @Column
    private String password;

    @Column
    private Boolean is_admin;

    @Column
    private Boolean is_authenticated;
}
