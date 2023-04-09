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
    @Getter @Setter
    @Id
    @Column(name="course_id")
    private String courseId;

    @Getter @Setter
    @Column(name="course_name")
    private String courseName;

    @Getter @Setter
    @Column(name="course_description")
    private String courseDescription;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

}
