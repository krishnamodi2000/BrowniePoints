package com.GRP3.BPA.model;

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
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private String teacher_id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
