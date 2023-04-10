package com.GRP3.BPA.model;

import com.GRP3.BPA.model.Teacher;
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


    /**
     * The ID of the course.
     */
    @Getter @Setter
    @Id
    @Column(name="course_id")
    private String courseId;


    /**
     * The name of the course.
     */
    @Getter @Setter
    @Column(name="course_name")
    private String courseName;

    /**
     * The description of the course.
     */
    @Getter @Setter
    @Column(name="course_description")
    private String courseDescription;

    /**
     * The teacher of the course.
     */
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

}
