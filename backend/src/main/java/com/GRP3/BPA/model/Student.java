package com.GRP3.BPA.model;

import com.GRP3.BPA.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student")
@AllArgsConstructor
@NoArgsConstructor


public class Student {

    /**
     * The banner ID of the student.
     */
    @Id
    @Getter @Setter
    @Column(name="banner_id")
    private String bannerId;

    /**
     * The user account associated with the student.
     */
    @Getter @Setter
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
