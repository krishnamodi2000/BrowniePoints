package com.GRP3.BPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="teacher_course")
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCourse {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

}
