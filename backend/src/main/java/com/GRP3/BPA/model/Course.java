package com.GRP3.BPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="course")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Getter @Setter
    @Id
   // @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String course_id;

    @Getter @Setter
    @Column
    private String course_name;

}
