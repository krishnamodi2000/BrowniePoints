package com.GRP3.BPA.model;

import com.GRP3.BPA.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="teacher")
@AllArgsConstructor
@NoArgsConstructor

public class Teacher {

    /**
     * The ID of the teacher.
     */
    @Id
    @Getter @Setter
    @Column(name="teacher_id")
    private String teacherId;

    /**
     * The user account associated with the teacher.
     */
    @Getter@Setter
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
