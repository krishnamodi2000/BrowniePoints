package com.GRP3.BPA.model;


import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

@Entity
@Table(name="User")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String email_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private Integer contact_number;

    @Column
    private String password;

    @Column
    private Boolean is_admin;

    @Column
    private Boolean is_authenticated;

    public User(){

    }

    public User(int id, String email_id, String net_id, String first_name, String last_name, int contact_number, String university_id, String password, Boolean is_admin, Boolean is_authenticated) {
        this.email_id = email_id;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.password = password;
        this.is_admin = is_admin;
        this.is_authenticated = is_authenticated;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Boolean getIs_authenticated() {
        return is_authenticated;
    }

    public void setIs_authenticated(Boolean is_authenticated) {
        this.is_authenticated = is_authenticated;
    }
}
